package com.oodles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.oodles.domain.User;
import com.oodles.dto.EmailDto;
import com.oodles.dto.EmailVerifyDto;
import com.oodles.dto.OtpDto;
import com.oodles.dto.UserDto;
import com.oodles.repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component("shivam4848@gmail.com")
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	private static JavaMailSender javaMailSender;

	@Autowired
	public UserService(JavaMailSender javaMailSender) {
		UserService.javaMailSender = javaMailSender;
	}

	private Map<String, OtpDto> otp_data = new HashMap<>();
	private Map<String, EmailDto> email_data = new HashMap<>();
	private String id ;
	// get it from properties file
	private final static String ACCOUNT_SID = "ACe2f058ec28715b76bdeaac2da52d1324";
	private final static String AUTH_ID = "66384b401747451a0cf47404f3ae8d4c";
	static {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}
	/**
	 * Creating new User
	 * @param user
	 * @return
	 */
	public Map<Object, Object> createUser(UserDto user) {
		Map<Object, Object> result = new HashMap<>();
		String name = user.getName();
		String email = user.getEmail();
		String phoneNumber = user.getPhoneNumber();
		String country = user.getCountry();
		String password = user.getPassword();
		User userEmail = userRepository.findByEmail(email);
		User userNumber = userRepository.findByPhoneNumber(phoneNumber);
		if (userEmail == null && userNumber == null) {
			User newUser = new User();
			newUser.setName(name);
			newUser.setEmail(email);
			newUser.setPhoneNumber(phoneNumber);
			newUser.setPassword(password);
			newUser.setCountry(country);
			userRepository.save(newUser);
			result.put("responseMessage", "Welcome to our Trade Exchnage");
			return result;
		}
		result.put("responseMessage", "Mail or phone number is present");
		return result;
	}

	/**
	 *  get all users
	 * @return
	 */
	public List<User> retrieveAllUser() {
		return userRepository.findAll();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
/*	public Optional<User> 
	}*/
	
	public Map findUserById(String id) {
		Map<Object, Optional<User>> result = new HashMap<>();
		Map<Object, String> output = new HashMap<>();
		Optional<User> value = userRepository.findById(Long.parseLong(id));
		if (value.isPresent()) {
			result.put("responseMessage", value);
			return result;
		}
		output.put("responseMessage", "User Not found");
		return output;
	}
		

	/** delete user by id
	 * 
	 * @param id
	 * @return
	 */
	public Map<Object, Object> deleteUser(String id) {
		Map<Object, Object> result = new HashMap<>();
		Optional<User> value = userRepository.findById(Long.parseLong(id));
		if (value.isPresent()) {
			User user = value.get();
			userRepository.deleteById(Long.parseLong(id));
			result.put("responseMessaage", "User is deleted");
		}
		return result;
	}

	/**
	 *  update user
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param phoneNumber
	 * @param country
	 * @return
	 */
	public User updateUser(String id, String name, String email, String password, String phoneNumber, String country) {
		Optional<User> value = userRepository.findById(Long.parseLong(id));
		User user = value.get();
		if (value.isPresent() && (!user.getName().equalsIgnoreCase(name)) && (!user.getEmail().equalsIgnoreCase(email))
				&& (!user.getPassword().equalsIgnoreCase(password))
				&& (!user.getPhoneNumber().equalsIgnoreCase(phoneNumber))) {
			User newUsers = new User();
			newUsers.setId(Long.parseLong(id));
			newUsers.setName(name);
			newUsers.setCountry(country);
			newUsers.setEmail(email);
			newUsers.setPhoneNumber(phoneNumber);
			newUsers.setPassword(password);
			userRepository.save(newUsers);
		}
		return user;
	}

	public Map updateUserRow(String id, String name, String email, String password, String phoneNumber, String country) {
		Map<Object, Object> result = new HashMap<>();
		Optional<User> value = userRepository.findById(Long.parseLong(id));
		if(value.isPresent()) {
			User user = value.get();
			if(name!=null) {
				user.setName(name);
				userRepository.save(user);
				result.put("responseMessageForName", "User name is update");
				return result;
			}
			else if(email != null) {
				
				user.setEmail(email);
				userRepository.save(user);
				result.put("responseMessageForEmail", "Name of the is update");
				return result;
			}
			
			
		}
		result.put("responseMessage", "User Is Not found");
		return result;
	}
	/**
	 * Send OTP
	 * @param userId
	 * @return
	 */
	public String sendOTP(String userId) {
		Optional<User> value = userRepository.findById(Long.parseLong(userId));
		User user = value.get();
		if (value.isPresent() && user.getPhoneNumber() != null) {
			String otpCode = String.valueOf((int) (Math.random() * (10000 - 1000)) + 1000);
			OtpDto otp = new OtpDto();
			otp.setMobileNumber(user.getPhoneNumber());
			otp.setOtp(String.valueOf(otpCode));
			otp.setExpirytime(System.currentTimeMillis() + 500000);
			user.setMobileCode(otpCode);
			userRepository.save(user);
			otp_data.put(user.getPhoneNumber(), otp);
			id = userId;
			Message.creator(new PhoneNumber("+918700153661"), new PhoneNumber("+19852384430"),
					"Your OTP is" + "" + otp.getOtp()).create();
			return "Your OTP send successfully..!!";
		}
		return "User not found";
	}

	/**
	 *  verify otp
	 * @param mobilenumber
	 * @param requestOTP
	 * @return
	 */
	public String verifyOtp(String mobilenumber, OtpDto requestOTP) {
		User mobileNumber = userRepository.findByPhoneNumber(requestOTP.getMobileNumber());
		if (requestOTP.getOtp() == null || requestOTP.getOtp().trim().length() <= 0) {
			return "Please provide OTP";
		}
		if (otp_data.containsKey(mobilenumber)) {
			OtpDto otp = otp_data.get(mobilenumber);
			if (otp != null) {
				if (otp.getExpirytime() >= System.currentTimeMillis()) {
					if (requestOTP.getOtp().equals(otp.getOtp())) {

						return "OTP is verified successfully";
					}
					return "OTP is invalid";
				}
				return "OTP is expired";
			}
			return "Something went wrong";
		}
		return "Mobile number is not found";
	}

	/**
	 *  Send mail
	 * @param userId
	 * @return
	 */
	public String sendMail(String userId) {
		Optional<User> value = userRepository.findById(Long.parseLong(userId));
		User user = value.get();
		String emailTo = user.getEmail();
		String otpCode = String.valueOf((int) (Math.random() * (10000 - 1000)) + 1000);
		String body = "Your Verification OTP-" + otpCode;
		EmailDto verifyEmail = new EmailDto();
		verifyEmail.setEmail(emailTo);
		verifyEmail.setExpirytime(System.currentTimeMillis() + 200000);
		verifyEmail.setOtp(otpCode);
		email_data.put(emailTo, verifyEmail);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("shivam4848@gmail.com");
		mail.setTo(emailTo);
		mail.setSubject("Please verify your OTP");
		mail.setText(body);
		user.setEmailCode(otpCode);
		userRepository.save(user);
		id = userId;
		javaMailSender.send(mail);
		return "Your verification code has been send to your mail";
	}

	/**
	 *  Verify Email
	 * @param email
	 * @param verifyEmail
	 * @return
	 */
	public String verifyEmail(String email, EmailDto verifyEmail) {
		if (verifyEmail.getOtp() == null || verifyEmail.getOtp().trim().length() <= 0) {
			return "Please provide Verification code";
		}
		if (email_data.containsKey(email)) {
			EmailDto emailDto = email_data.get(email);
			if (emailDto != null) {
				if (emailDto.getExpirytime() >= System.currentTimeMillis()) {
					if (verifyEmail.getOtp().equals(emailDto.getOtp())) {
						Optional<User> value = userRepository.findById(Long.parseLong(id));
						if (value.isPresent()) {
							User user = value.get();
							String emailCode = user.getEmailCode();
							String mobileCode = user.getMobileCode();
							if (emailCode != null && mobileCode != null) {
								user.setStatus("active");
								userRepository.save(user);
							}
						}
						email_data.remove(email);
						return "Verificarion code is verified successfully";
					}
					return "Verfication code is invalid";
				}
				return "Verification code is expired";
			}
			return "Something went wrong";
		}
		return "Email Address is not found";
	}

	/**
	 *  Forget Password sending verification password
	 * @param userId
	 * @return
	 */
	public String forgetPassword(String userId) {
		Optional<User> value = userRepository.findById(Long.parseLong(userId));
		User user = value.get();
		String emailTo = user.getEmail();
		String otpCode = String.valueOf((int) (Math.random() * (10000 - 1000)) + 1000);
		String body = "Your Verification OTP code for reset password -" + otpCode;
		EmailDto verifyEmail = new EmailDto();
		verifyEmail.setEmail(emailTo);
		verifyEmail.setExpirytime(System.currentTimeMillis() + 200000);
		verifyEmail.setOtp(otpCode);
		email_data.put(emailTo, verifyEmail);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("shivam4848@gmail.com");
		mail.setTo(emailTo);
		mail.setSubject("Please verify OTP for reset your password");
		mail.setText(body);
		user.setPassToken(otpCode);
		userRepository.save(user);
		id = userId;
		javaMailSender.send(mail);
		return "Your verification code has been send to your mail";
	}

	/**
	 *  Updating Password by sending verification mail
	 * @param email
	 * @param verifyEmail
	 * @return
	 */
	public String verifyEmailAndUpdatePass(String email, EmailVerifyDto verifyEmail) {
		String password = verifyEmail.getPassword();
		if (verifyEmail.getOtp() == null || verifyEmail.getOtp().trim().length() <= 0) {
			return "Please provide Verification code";
		}
		if (email_data.containsKey(email)) {
			EmailDto emailDto = email_data.get(email);
			if (emailDto != null) {
				if (emailDto.getExpirytime() >= System.currentTimeMillis()) {
					if (verifyEmail.getOtp().equals(emailDto.getOtp())) {
						Optional<User> foundUser = userRepository.findById(Long.parseLong(id));
						User user = foundUser.get();
						user.setPassword(password);
						userRepository.save(user);
						email_data.remove(email);
						return "Your Password is Updated";
					}
					return "Verfication code is invalid";
				}
				return "Verification code is expired";
			}
			return "Something went wrong";
		}
		return "Email Address is not found";
	}

}

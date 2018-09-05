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

import com.oodles.domain.Otp;
import com.oodles.domain.User;
import com.oodles.domain.VerifyEmail;
import com.oodles.repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component("shivam4848@gmail.com")
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	private static JavaMailSender javaMailSender;

	@Autowired
	public UserService(JavaMailSender javaMailSender) {
		UserService.javaMailSender = javaMailSender;
	}

	private Map<String, Otp> otp_data = new HashMap<>();
	private Map<String, VerifyEmail> email_data = new HashMap<>();
	// get it from properties file
	private final static String ACCOUNT_SID = "ACe2f058ec28715b76bdeaac2da52d1324";
	private final static String AUTH_ID = "66384b401747451a0cf47404f3ae8d4c";
	static {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}

	public Map<Object, Object> createUser(User user) {
		Map<Object, Object> result = new HashMap<>();
		String name = user.getName();
		String email = user.getEmail();
		String phoneNumber = user.getPhoneNumber();
		String country = user.getCountry();
		String password = user.getPassword();
		User userEmail = userRepository.findByEmail(email);
		User userNumber = userRepository.findByPhoneNumber(phoneNumber);
		// checking for existing email and mobile no
		if (userEmail == null && userNumber == null) {
			User newUser = new User();
			newUser.setName(name);
			newUser.setEmail(email);
			newUser.setPhoneNumber(phoneNumber);
			newUser.setPassword(password);
			newUser.setCountry(country);
			userRepository.save(newUser);
			result.put("responseMessage", "success");
			return result;
		}
		result.put("responseMessage", "error");
		return result;
	}

	// get all users
	public List<User> retrieveAllUser() {
		List<User> result = userRepository.findAll();
		return result;
	}

	// get all user by id
	public Optional<User> findUserById(String id) {
		Optional<User> value = userRepository.findById(Long.parseLong(id));
		User result = value.get();
		if (value.isPresent() && (!result.getId().equals(id))) {
			return value;
		}
		return value;
	}

	// delete user by id
	public User deleteUser(String id) {
		Optional<User> value = userRepository.findById(Long.parseLong(id));

		User result = value.get();
		if (value.isPresent() && (!result.getId().equals(id))) {
			userRepository.deleteById(Long.parseLong(id));
		}
		return result;
	}

	// update user
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
// Send OTP
	public String sendOTP(String userId) {
		Optional<User> value = userRepository.findById(Long.parseLong(userId));
		User user = value.get();
		String mobilenumber = user.getPhoneNumber();
		System.out.println(mobilenumber);
		Otp otp = new Otp();
		otp.setMobileNumber(user.getPhoneNumber());
		otp.setOtp(String.valueOf((int) (Math.random() * (10000 - 1000)) + 1000));
		otp.setExpirytime(System.currentTimeMillis() + 500000);
		otp_data.put(user.getPhoneNumber(), otp);
		Message.creator(new PhoneNumber("+918700153661"), new PhoneNumber("+19852384430"),
				"Your OTP is" + "" + otp.getOtp()).create();
		return "Yor OTP send successfully..!!";
	}
	// verify otp
	public String verifyOtp(String mobilenumber, Otp requestOTP) {
		if (requestOTP.getOtp() == null || requestOTP.getOtp().trim().length() <= 0) {
			return "Please provide OTP";
		}
		if (otp_data.containsKey(mobilenumber)) {
			Otp otp = otp_data.get(mobilenumber);
			if (otp != null) {
				if (otp.getExpirytime() >= System.currentTimeMillis()) {
					if (requestOTP.getOtp().equals(otp.getOtp())) {
						otp_data.remove(mobilenumber);
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
	//  Send mail
	public String sendMail(String userId) {
		Optional<User> value = userRepository.findById(Long.parseLong(userId));
		User user = value.get();
		String emailTo = user.getEmail();
		String otpCode = String.valueOf((int) (Math.random() * (10000 - 1000)) + 1000);
		String body = "Your Verification OTP-" + otpCode;
		VerifyEmail verifyEmail = new VerifyEmail();
		verifyEmail.setEmail(emailTo);
		verifyEmail.setExpirytime(System.currentTimeMillis() + 200000);
		verifyEmail.setOtp(otpCode);
		email_data.put(emailTo, verifyEmail);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("shivam4848@gmail.com");
		mail.setTo(emailTo);
		mail.setSubject("Please verify your OTP");
		mail.setText(body);
		javaMailSender.send(mail);
		return "Your verification code has been send to your mail";
	}
	
	public String verifyEmail(String email , VerifyEmail verifyEmail) {
		if (verifyEmail.getOtp() == null || verifyEmail.getOtp().trim().length() <= 0) {
			return "Please provide Verification code";
		}
		if (email_data.containsKey(email)) {
			Otp otp = otp_data.get(email);
			if (otp != null) {
				if (otp.getExpirytime() >= System.currentTimeMillis()) {
					if (verifyEmail.getOtp().equals(otp.getOtp())) {
						email_data.remove(email);
						return "OTP is verified successfully";
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

package com.oodles.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.oodles.domain.FiatWallet;
import com.oodles.domain.Role;
import com.oodles.domain.User;
import com.oodles.repository.FiatWalletRepository;
import com.oodles.repository.RoleRepository;
import com.oodles.repository.UserRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private FiatWalletRepository fiatWalletRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	// API

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}

		Role role = roleRepository.findByRoleType("ADMIN");
		if (role == null) {
			role = new Role();
			role.setRoleType("ADMIN");
			role = roleRepository.save(role);
		}
		Role role2 = roleRepository.findByRoleType("USER");
		if (role2 == null) {
			role2 = new Role();
			role2.setRoleType("USER");
			role2 = roleRepository.save(role2);
		}
		User userEmail = userRepository.findByEmail("admin@gmail.com");
		User userNumber = userRepository.findByPhoneNumber("9998887776");
		if (userEmail == null && userNumber == null) {
			Role findRole1 = roleRepository.findByRoleType("ADMIN");
			Role findRole2 = roleRepository.findByRoleType("USER");;
			User newUser = new User();
			HashSet<Role> roleSet = new HashSet<>();
			roleSet.add(findRole1);
			roleSet.add(findRole2);
			HashSet<User> userSet = new HashSet<>();
			userSet.add(newUser);
			newUser.setName("Admin");
			newUser.setEmail("admin@gmail.com");
			newUser.setPhoneNumber("9998887776");
			newUser.setPassword(bcryptEncoder.encode("Admin@123"));
			newUser.setCountry("India");
			newUser.setEmailCode("0000");
			newUser.setMobileCode("0000");
			newUser.setPassToken("0000");
			newUser.setExpiryTimeOfEmail((long) 000000000000);
			newUser.setExpiryTimeOfOtp((long) 000000000000);
			newUser.setStatus("Active");
			newUser.setRoles(roleSet);
			role.setUsers(userSet);
			userRepository.save(newUser);
		}
		Optional<User> user = userRepository.findById((long) 3);
		if (user.isPresent()) {
			User foundUser = user.get();
			FiatWallet newWalletType = fiatWalletRepository.findByUser(foundUser);
			if (newWalletType == null) {
				FiatWallet wallet = new FiatWallet();
				wallet.setCoinName("RUPEES");
				wallet.setWalletType("FIAT");
				wallet.setShadowBalance(0.0);
				wallet.setBalance(0.0);
				wallet.setUser(foundUser);
				fiatWalletRepository.save(wallet);
			}
		}
		alreadySetup = true;
	}

}
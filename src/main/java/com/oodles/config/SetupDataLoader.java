package com.oodles.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.oodles.models.Role;
import com.oodles.models.User;
import com.oodles.repository.RoleRepository;
import com.oodles.repository.UserRepository;

@Component

public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }final Role adminRole = createRoleIfNotFound("ADMIN");
        createRoleIfNotFound("USER");
        createRoleIfNotFound("SubAdmin");
        createUserIfNotFound("TradeExchange", "trade@gmail.com", "9089754667", "Test@123","India", new HashSet<Role>(Arrays.asList(adminRole)) );

        alreadySetup = true;
    }
    @Transactional
    private final Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByroleType(name);
        if (role == null) {
            role = new Role(name);
        }
        
        role = roleRepository.save(role);
        return role;
    }
        @Transactional
        private final User createUserIfNotFound(final String name, final String email, final String mobilenumber, final String password,final String country,final Set<Role> roles) {
            User user = userRepository.findByEmail(email);
            if (user == null) {
            	user = new User();
                
    			//User newUser=new User();
    			user.setName(name);
    			user.setEmail(email);
    			user.setMobilenumber(mobilenumber);
    			user.setPassword(bCryptPasswordEncoder.encode(password));
    			user.setCountry(country);
    			user.setEnabled("Active");
           
    			
            }
            user.setRole(roles);
            //user.setRoles(roles);
            user = userRepository.save(user);
            return user;
          

    }
        
        
        
}

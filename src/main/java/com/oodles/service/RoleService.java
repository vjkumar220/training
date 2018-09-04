package com.oodles.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.Role;
import com.oodles.domain.User;
import com.oodles.repository.RoleRepository;
import com.oodles.repository.UserRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;

	// Creting Role
	public Map<Object, Object> createRole(Role role) {
		Map<Object, Object> result = new HashMap<>();
		String roleType = role.getRoleType();
		Role userRoleype = roleRepository.findByRoleType(roleType);
		if (userRoleype == null) {
			Role newRole = new Role();
			newRole.setRoleType(roleType);
			roleRepository.save(newRole);
			result.put("responseMessage", "success");
			return result;
		}
		result.put("responseMessage", "error");
		return result;
	}

	public Map<?, ?> assingUserToRole(String roleType, String id) {
		Role role = roleRepository.findByRoleType(roleType);
		Optional<User> user = userRepository.findById(Long.parseLong(id));

		return null;
	}
}

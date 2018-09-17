package com.oodles.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.Role;
import com.oodles.domain.User;
import com.oodles.dto.UserRoleDto;
import com.oodles.repository.RoleRepository;
import com.oodles.repository.UserRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	/**
	 *  Creating Role
	 * @param role
	 * @return
	 */
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

	/**
	 *  Assign Role
	 * @param userRoleDto
	 * @return
	 */
	public Map<Object, Object> assignRole(UserRoleDto userRoleDto) {
		Map<Object, Object> result = new HashMap<>();
		Long userId = userRoleDto.getUserId();
		Long roleId = userRoleDto.getRoleId();
		Optional<User> userValue = userRepository.findById(userId);
		Optional<Role> roleValue = roleRepository.findById(roleId);
		if (userValue.isPresent() && roleValue.isPresent()) {
			User foundUser = userValue.get();
			Role foundRole = roleValue.get();
			HashSet<Role> roleSet = new HashSet<>();
			roleSet.add(foundRole);
			HashSet<User> userSet = new HashSet<>();
			userSet.add(foundUser);
			foundUser.setRoles(roleSet);
			foundRole.setUsers(userSet);
			userRepository.save(foundUser);
			result.put("responseMessage", "success");
			return result;
		} else {
			result.put("responseMessage", "error");
			return result;
		}
	}
}

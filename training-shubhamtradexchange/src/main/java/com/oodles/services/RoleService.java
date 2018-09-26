package com.oodles.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.dto.RoleDto;
import com.oodles.models.Role;
import com.oodles.models.User;
import com.oodles.repository.RoleRepository;
import com.oodles.repository.UserRepository;
@Service
public class RoleService {
@Autowired
private RoleRepository roleRepository;
@Autowired
private UserRepository userRepository;
/**
 *  create a new role
 * @param roleType
 * @return
 */
	public Map<String, Object> createRole(String roleType) {
		Map<String, Object> result = new HashMap<String, Object>();
		Role role = roleRepository.findByroleType(roleType);
		if(role == null){
			
			Role newRole=new Role();
			newRole.setRoleType(roleType);	
			roleRepository.save(newRole);
			
			result.put("responseMessage", "success");
		}
		return result;
	}
	/**
	 *  Assign Role
	 * @param userRoleDto
	 * @return
	 */
		public Map<String,Object> assignRole(RoleDto userRoleDto) {
			Map<String, Object> result = new HashMap<>();
			Long userId = userRoleDto.getUserId();
			Long roleId = userRoleDto.getRoleId();
			
			Optional<User> userValue=userRepository.findById(userId);
			Optional<Role> roleValue=roleRepository.findByroleId(roleId);
			
			if(userValue.isPresent() && roleValue.isPresent())
			{
				User foundUser=userValue.get();
				Role foundRole=roleValue.get();
				
				HashSet roleSet=new HashSet<>();
				roleSet.add(foundRole);
				HashSet userSet = new HashSet<>();
				userSet.add(foundUser);
				foundUser.setRole(roleSet);
				foundRole.setUser(userSet);
				userRepository.save(foundUser);
				result.put("responseMessage", "success");
				return result;
			}
			else
			{
				result.put("responseMessage", "error");
			return result;
			}}}
		
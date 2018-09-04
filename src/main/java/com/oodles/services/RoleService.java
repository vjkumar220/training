package com.oodles.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.models.Role;
import com.oodles.repository.RoleRepository;

@Service
public class RoleService {
@Autowired
private RoleRepository roleRepository;
	// create a new role
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
	// create a new admin
	
	
}

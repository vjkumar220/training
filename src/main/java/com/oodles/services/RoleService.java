package com.oodles.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.models.Role;
import com.oodles.models.RoleDTO;
import com.oodles.models.User;
import com.oodles.repository.RoleRepository;
import com.oodles.repository.UserRepository;

@Service
public class RoleService {
@Autowired
private RoleRepository roleRepository;
@Autowired
private UserRepository userRepository;
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
	public Map<Long,Object> assignRole(RoleDTO roleDTO)
	{
	Map<Long,Object> result=new HashMap<Long,Object>();
	Long id=roleDTO.getUserId();
	Long roleid=roleDTO.getRoleId(); 	
		
	//Role role=new Role();
	
	if(!(id==null) &&(!(roleid==null)))
	{
		User newuser=new User();
        newuser.setId(id);
        Optional<Role>role=roleRepository.findByroleId(roleid);
		if(role.isPresent())
		{
		newuser.setRole((Set<Role>) role.get());
		userRepository.save(newuser);
		//result.put("responseMessage", "success");
		}
	}return result;
	}}
	

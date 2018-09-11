package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.user.Role;

public interface RoleRepository  extends JpaRepository<Role, Long>{
	
	Role findByRoleType(String roleType);

}

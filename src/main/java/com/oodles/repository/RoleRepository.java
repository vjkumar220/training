package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.Role;
@Repository
public interface RoleRepository  extends JpaRepository<Role, Long>{
	
	Role findByRoleType(String roleType);

}

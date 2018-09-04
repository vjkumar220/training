package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	Role findByroleType(String roleType);
	}

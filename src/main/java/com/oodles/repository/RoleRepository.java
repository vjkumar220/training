package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.Role;

public interface RoleRepository  extends JpaRepository<Role, Long>{

}

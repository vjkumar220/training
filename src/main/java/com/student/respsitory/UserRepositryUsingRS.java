package com.student.respsitory;


import org.springframework.data.jpa.repository.JpaRepository;

import com.student.domain.UserUsingRH;

public interface UserRepositryUsingRS extends JpaRepository<UserUsingRH, Long>{
	
	 UserUsingRH findByName(String name);

}

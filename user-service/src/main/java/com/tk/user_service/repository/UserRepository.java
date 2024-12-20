package com.tk.user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tk.user_service.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
	Users findByUsername(String userName);
	
	Users findByUserId(int userId);
}

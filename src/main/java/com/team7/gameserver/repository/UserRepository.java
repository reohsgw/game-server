package com.team7.gameserver.repository;


import com.team7.gameserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//repository for accessing the users table 
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
}
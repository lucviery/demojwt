package com.example.jwt.demojwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwt.demojwt.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findOneByLogin(String login);
}

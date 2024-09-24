package com.springauth.SpringAuth.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String username);
	Optional<User> findById(Long userId);
}

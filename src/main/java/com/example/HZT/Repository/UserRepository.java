package com.example.HZT.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HZT.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findById(Long id);

	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
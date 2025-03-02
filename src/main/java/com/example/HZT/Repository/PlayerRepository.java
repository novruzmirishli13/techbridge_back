package com.example.HZT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HZT.Entity.PlayerState;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerState, Long> {
	
	
}

package com.example.HZT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HZT.Model.UserProgress;
import java.util.Optional;


public interface UserProgressRepository extends JpaRepository<UserProgress, Long>{
    Optional<UserProgress> findByUsername(String username);

}

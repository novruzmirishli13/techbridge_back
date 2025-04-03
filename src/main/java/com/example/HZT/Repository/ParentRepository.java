package com.example.HZT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HZT.Entity.Parent;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    long count();
  
}

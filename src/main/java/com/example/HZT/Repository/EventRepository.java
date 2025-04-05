package com.example.HZT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HZT.Entity.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
    
}

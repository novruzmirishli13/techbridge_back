package com.example.HZT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HZT.Entity.Region;

public interface RegionRepository extends JpaRepository<Region, Long>{
    long count();
   
}

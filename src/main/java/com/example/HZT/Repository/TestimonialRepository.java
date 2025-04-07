package com.example.HZT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HZT.Entity.Testimonial;

public interface TestimonialRepository extends JpaRepository<Testimonial, Long>{
    
}

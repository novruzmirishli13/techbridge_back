package com.example.HZT.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HZT.Entity.Testimonial;
import com.example.HZT.Repository.TestimonialRepository;

@Service
public class TestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    public List<Testimonial> getAllTestimonials() {
        return testimonialRepository.findAll();
    }

    public Testimonial save(Testimonial testimonial) {
        return testimonialRepository.save(testimonial);
    }
}

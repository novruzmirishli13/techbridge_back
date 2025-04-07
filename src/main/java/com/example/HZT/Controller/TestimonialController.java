package com.example.HZT.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.HZT.Entity.Testimonial;
import com.example.HZT.Repository.TestimonialRepository;

@RestController
@RequestMapping("/api/testimonials")
public class TestimonialController {

    @Autowired
    private TestimonialRepository testimonialRepository;

    @PostMapping("/add")
    public ResponseEntity<Testimonial> addTestimonial(@RequestBody Testimonial testimonial) {
        Testimonial savedTestimonial = testimonialRepository.save(testimonial);
        return ResponseEntity.ok(savedTestimonial);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Testimonial> getTestimonial(@PathVariable("id") Long id) {
        Optional<Testimonial> testimonial = testimonialRepository.findById(id);

        if (testimonial.isPresent()) {
            return ResponseEntity.ok(testimonial.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

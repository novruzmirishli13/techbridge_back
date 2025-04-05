package com.example.HZT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.HZT.Entity.Event;
import com.example.HZT.Repository.EventRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.*;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    
    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/upload/{eventId}")
    public ResponseEntity<String> uploadImage(
            @PathVariable("eventId") Long eventId,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
    
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new RuntimeException("Event not found"));
            event.setImageUrl("/api/images/" + fileName);
            eventRepository.save(event);
    
            return ResponseEntity.ok("Image uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image");
        }
    }
    

    @GetMapping("/{fileName}")
public ResponseEntity<Resource> getImage(@PathVariable("fileName") String fileName) {
    try {
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists()) {
            return ResponseEntity.ok()
                    .header("Content-Type", Files.probeContentType(filePath))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
}

package com.example.HZT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HZT.Service.ParentService;
import com.example.HZT.Service.RegionService;
import com.example.HZT.Service.StudentService;
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private RegionService regionService;

    @GetMapping("/students/count")
    public ResponseEntity<Long> getStudentCount() {
        long count = studentService.getStudentCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/parents/count")
    public ResponseEntity<Long> getParentCount() {
        long count = parentService.getParentCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/regions/count")
    public ResponseEntity<Long> getRegionCount() {
        long count = regionService.getRegionCount();
        return ResponseEntity.ok(count);
    }
}
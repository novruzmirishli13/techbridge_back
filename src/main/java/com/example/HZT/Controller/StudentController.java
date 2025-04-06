package com.example.HZT.Controller;

import com.example.HZT.Dto.CountsDto;
import com.example.HZT.Service.ParentService;
import com.example.HZT.Service.RegionService;
import com.example.HZT.Service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private RegionService regionService;

    @GetMapping("/count")
    public ResponseEntity<CountsDto> getAllCount() {
        long students = studentService.getStudentCount();
        long parents = parentService.getParentCount();
        long regions = regionService.getRegionCount();

        // Create the CountsDTO object
        CountsDto countsDTO = new CountsDto(students, parents, regions);

        // Return all counts in a single response
        return ResponseEntity.ok(countsDTO);
    }
}

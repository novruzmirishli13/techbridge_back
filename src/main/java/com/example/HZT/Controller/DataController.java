package com.example.HZT.Controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HZT.Service.DataService;

@RestController
@RequestMapping("/api")
public class DataController {
    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/counts")
    public Map<String, Long> getCounts() {
        return Map.of(
                "students", dataService.getStudentCount(),
                "parents", dataService.getParentCount(),
                "regions", dataService.getRegionCount()
        );
    }
}
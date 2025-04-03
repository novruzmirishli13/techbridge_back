package com.example.HZT.Service;

import org.springframework.stereotype.Service;
import com.example.HZT.Repository.ParentRepository;
import com.example.HZT.Repository.RegionRepository;
import com.example.HZT.Repository.UserRepository;

@Service
public class DataService {

    private final UserRepository studentRepository;
    private final ParentRepository parentRepository;
    private final RegionRepository regionRepository;

    public DataService(UserRepository studentRepository, ParentRepository parentRepository, RegionRepository regionRepository) {
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
        this.regionRepository = regionRepository;
    }

    public long getStudentCount() {
        return (3);
    }

    public long getParentCount() {
        return (4);
    }

    public long getRegionCount() {
        return (5);
    }
}

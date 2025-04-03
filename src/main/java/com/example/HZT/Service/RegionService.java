package com.example.HZT.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HZT.Repository.RegionRepository;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public long getRegionCount() {
        return regionRepository.count();
    }
}
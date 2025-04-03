package com.example.HZT.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HZT.Repository.ParentRepository;

@Service
public class ParentService {

    @Autowired
    private ParentRepository parentRepository;

    public long getParentCount() {
        return parentRepository.count();
    }
}
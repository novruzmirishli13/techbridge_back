package com.example.HZT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HZT.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    long count();
}

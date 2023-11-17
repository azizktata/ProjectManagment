package com.example.project.PDS.repository;

import com.example.project.PDS.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface StudentRepo extends MongoRepository<Student, String> {
}

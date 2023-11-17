package com.example.project.PDS.repository;

import com.example.project.PDS.models.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface ProjectRepo extends MongoRepository<Project, String> {
}

package com.example.project.PDS.repository;

import com.example.project.PDS.models.Supervisor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface SupervisorRepo extends MongoRepository <Supervisor, String> {
    Supervisor findByName(String name);
}

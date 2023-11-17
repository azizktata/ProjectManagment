package com.example.project.PDS.repository;

import com.example.project.PDS.models.Stage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StagesRepo extends MongoRepository<Stage,String> {
}

package com.example.project.PDS.Initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

//@Component
//public class DatabaseInitializer implements CommandLineRunner {
//
//    private final MongoTemplate mongoTemplate;
//
//    @Autowired
//    public DatabaseInitializer(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Drop collections or perform other initialization tasks
//        mongoTemplate.getDb().drop();
//        // Alternatively, you can drop specific collections:
//        // mongoTemplate.dropCollection("yourCollectionName");
//    }
//}

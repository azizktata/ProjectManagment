//package com.example.project.PDS.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//
//@Configuration
//public class MongoConfig extends AbstractMongoClientConfiguration {
//
//    @Override
//    protected String getDatabaseName() {
//        return "pdsdb";
//    }
//
//    @Override
//    public MongoClient mongoClient() {
//        return MongoClients.create("mongodb://rootuser:rootpass@localhost:27017/");
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate() throws Exception {
//        return new MongoTemplate(mongoClient(), getDatabaseName());
//    }
//}

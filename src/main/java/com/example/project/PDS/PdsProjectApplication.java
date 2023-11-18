package com.example.project.PDS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class PdsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdsProjectApplication.class, args);
	}

}

package com.example.project.PDS.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    private String id;

    private String name;
    private String email;

    @DBRef
    private Project project;

    private Team team;


    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

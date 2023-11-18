package com.example.project.PDS.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "supervisors")
@Data
@NoArgsConstructor
public class Supervisor {

    @Id
    private String id;

    private String name;
    private String email;

    @DBRef(lazy = false)
    private List<Project> projects;


    public Supervisor(String name, String email) {
        this.name = name;
        this.email = email;
        this.projects = new ArrayList<>();
    }

}

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

@Document(collection = "stages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stage {

    @Id
    private String id;

    private String name;

    private List<Task> tasks;

    private List<String> comments;

    @DBRef
    private Project project;

    public Stage(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }
    public void addComments(String comment){
        comments.add(comment);
    }
}
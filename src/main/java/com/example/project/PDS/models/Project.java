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


@Document(collection = "projects")
@Data
@NoArgsConstructor
public class Project {

    @Id
    private String id;

    private String title;
    private String description;
    private String timeline;
    private Doccument document;

    @DBRef
    private Supervisor supervisorId; // Reference to Supervisor

    private Team team;

    @DBRef
    private List<Stage> stages;

    public Project(String title, String description, String timeline, Team team) {
        this.title = title;
        this.description = description;
        this.timeline = timeline;
        this.team = team;
        this.stages = new ArrayList<>();
    }

    public void addStage(Stage stage){
        stages.add(stage);
    }


}

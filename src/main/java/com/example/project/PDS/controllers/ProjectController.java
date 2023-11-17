package com.example.project.PDS.controllers;

import com.example.project.PDS.DTO.projectDTO;
import com.example.project.PDS.DTO.userDTO;
import com.example.project.PDS.models.Project;
import com.example.project.PDS.models.Stage;
import com.example.project.PDS.models.Student;
import com.example.project.PDS.services.ProjectService;
import com.example.project.PDS.services.StagesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController(value = "/api/v1/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final StagesService stagesService;

    @GetMapping()
    public List<Project> getAllProjects(){return projectService.getAllProjects();}

    @GetMapping(value ="/{Id}")
    public Project getProject(@PathVariable String Id){return projectService.getProject(Id);}

    // Add stage to project
    @PostMapping(value = "/{Id}/stages")
    public String createStage(@PathVariable String Id){return stagesService.createStage(Id);}


    //Queries
        //get projects by supervisor
        // get stages by project
        // get tasks by stages
        // get comments by stage

}

package com.example.project.PDS.controllers;

import com.example.project.PDS.DTO.projectDTO;
import com.example.project.PDS.DTO.stageDTO;
import com.example.project.PDS.DTO.taskDTO;
import com.example.project.PDS.DTO.userDTO;
import com.example.project.PDS.models.*;
import com.example.project.PDS.services.ProjectService;
import com.example.project.PDS.services.StagesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final StagesService stagesService;

    @Operation(summary = "Get All Projects ")
    @GetMapping()
    public List<Project> getProjects(){return projectService.getAllProjects();}

    @Operation(summary = "Get Project By Id")
    @GetMapping(value ="/{projectId}")
    public Project getProject(@PathVariable String projectId){return projectService.getProject(projectId);}

    //get stages by project
    @Operation(summary = "Get Stages by Project Id")
    @GetMapping(value ="/{projectId}/stages")
    public List<Stage> getStagesProject(@PathVariable String projectId){return projectService.getStageByProject(projectId);}

    //get tasks by stage
    @Operation(summary = "Get Tasks by Stage Id")
    @GetMapping(value ="/stages/{stageId}/tasks")
    public List<Task> getTasksStage(@PathVariable String stageId){return projectService.getTasksByStage(stageId);}

    @Operation(summary = "Get Comments by Stage Id")
    @GetMapping(value ="/stages/{stageId}/comments")
    public List<Comment> getCommentsStage(@PathVariable String stageId){return projectService.getCommentsByStage(stageId);}


    // Add stage to project
    @Operation(summary = "Add stage to a Project")
    @PostMapping(value = "/{projectId}/stages")
    public String addStage(@PathVariable String projectId,@RequestBody stageDTO stageDto){return stagesService.createStage(projectId,stageDto);}


    //Add a document
    @Operation(summary = "Upload specification book PDF")
    @PostMapping(value ="/{projectId}/document/spec")
    public String uploadSpec(@PathVariable String projectId) {return projectService.uploadSpec(projectId);}

    @Operation(summary = "Upload Class Diagram PDF")
    @PostMapping(value ="/{projectId}/document/classD")
    public String uploadClassDiagram(@PathVariable String projectId) {return projectService.uploadClassD(projectId);}

    @Operation(summary = "Upload use case PDF")
    @PostMapping(value ="/{projectId}/document/useCase")
    public String uploadUseCaseDiagram(@PathVariable String projectId) {return projectService.uploadUseCaseD(projectId);}

    // add specification file
    // add Class diagram file
    // add Use Case file
}

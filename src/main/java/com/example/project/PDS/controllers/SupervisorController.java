package com.example.project.PDS.controllers;

import com.example.project.PDS.DTO.projectDTO;
import com.example.project.PDS.DTO.userDTO;
import com.example.project.PDS.models.Stage;
import com.example.project.PDS.models.Student;
import com.example.project.PDS.models.Supervisor;
import com.example.project.PDS.services.SupervisorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController(value = "/api/v1/supervisors")
public class SupervisorController {
    private final SupervisorService supervisorService;

    @GetMapping()
    public List<Supervisor> getAllSupervisors(){return supervisorService.getAllSupervisor();}

    @GetMapping(value ="/{Id}")
    public Supervisor getSupervisor(@PathVariable String Id){return supervisorService.getSupervisor(Id);}

    @PostMapping()
    public String addSupervisor(@RequestBody userDTO userDto){return supervisorService.createSupervisor(userDto);}

    @DeleteMapping(value ="/{Id}")
    public String removeSupervisor(@PathVariable String Id){return supervisorService.deleteSupervisor(Id);}

    //create project
    @PostMapping(value ="/{Id}")
    public String addProject(@PathVariable String Id,@RequestBody projectDTO projectDto){return supervisorService.AddProject(Id,projectDto);}

    // add comment to stage /only a supervisor can add comment
    @PostMapping(value ="/stages/{stageId}/comments")
    public Stage addComment(@PathVariable String stageId, @RequestBody String comment){return supervisorService.addComment(stageId,comment);}

    // view his teams

    // view his projects

    // view stages-tasks by project



}

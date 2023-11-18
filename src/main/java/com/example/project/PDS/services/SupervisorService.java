package com.example.project.PDS.services;


import com.example.project.PDS.DTO.commentDTO;
import com.example.project.PDS.DTO.projectDTO;
import com.example.project.PDS.DTO.userDTO;
import com.example.project.PDS.models.Project;
import com.example.project.PDS.models.Stage;
import com.example.project.PDS.models.Supervisor;
import com.example.project.PDS.models.Team;
import com.example.project.PDS.repository.SupervisorRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class SupervisorService {
    private final SupervisorRepo supervisorRepo;
    private final ProjectService projectService;
    private final StagesService stagesService;

    // create supervisor
    public String createSupervisor(userDTO user){
        Supervisor supervisor = new Supervisor(
                user.name, user.email
        );
        return supervisorRepo.save(supervisor).getId();
    }
    // update supervisor
    // delete account
    public String deleteSupervisor(String Id){ //to Modify (delete all entities that he has relation with)
        Supervisor supervisor = supervisorRepo.findById(Id).get();
        supervisorRepo.delete(supervisor);
        return "supervisor "+supervisor.getName()+" is deleted";
    }

    // get supervisor
    public Supervisor getSupervisor(String Id){
        return supervisorRepo.findById(Id).get();
    }

    public List<Supervisor> getAllSupervisor(){
        return supervisorRepo.findAll();
    }



    // Add projects
    public String AddProject(String supervisorId, projectDTO project){
        return projectService.createProject(project ,supervisorId);
    }
    //// View project details
        // View/download document
        // view his projects / Teams
        // view project stages / tasks
    public List<Project> getMyProjects (String Id){
        Supervisor supervisor = supervisorRepo.findById(Id)
                .orElseThrow(() -> new RuntimeException("Supervisor not found"));
        return supervisor.getProjects();
    }

    // Add comments on each stage
    public Stage addComment(String stageId, commentDTO commentDto){
        return stagesService.addComment(stageId,commentDto);
    }

    public List<Team> getMyTeams(String Id) {
        Supervisor supervisor = supervisorRepo.findById(Id)
                .orElseThrow(() -> new RuntimeException("Supervisor not found"));
        List<Project> myProjects = supervisor.getProjects();
        List<Team> myTeams = new ArrayList<>();
        for(Project project : myProjects){
            myTeams.add(project.getTeam());
        }
        return myTeams;
    }

    public String removeComment(String stageId, String commentId) {
        Stage stage = stagesService.getStage(stageId);
        if (!stage.checkCommentId(commentId))
            throw new IllegalArgumentException("Comment with Id " + commentId + " not found in stage " + stageId);

        stage.removeCommentById(commentId);
        stagesService.saveStage(stage);
        return "Comment:" +" "+"'"+ commentId + "'"+" " + "is removed";
    }
    // Delete comments

    // Query / search / insights
}

package com.example.project.PDS.services;

import com.example.project.PDS.DTO.projectDTO;
import com.example.project.PDS.Exceptions.ObjectNotFoundException;
import com.example.project.PDS.models.*;
import com.example.project.PDS.repository.ProjectRepo;
import com.example.project.PDS.repository.SupervisorRepo;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProjectService {
    private ProjectRepo projectRepo;
    private final SupervisorRepo supervisorRepo;
    private final StagesService stagesService;

    //Create a project
    public String createProject(projectDTO projectDto, String supervisorId){

        Supervisor supervisor = supervisorRepo.findById(supervisorId)
                .orElseThrow(() -> new RuntimeException("Supervisor not found"));

        Team newTeam = new Team(
                projectDto.teamName
        );
        Member member = new Member(
                supervisor.getName(),supervisor.getEmail(),"supervisor"
        );
        newTeam.getMembers().add(member);


        Project newProject = new Project(
                projectDto.title,projectDto.description,projectDto.timeline,newTeam
        );
        newProject.setTeam(newTeam);

        newProject.setSupervisorId(supervisor);
        Project project = projectRepo.save(newProject);
        supervisor.getProjects().add(project);
        supervisorRepo.save(supervisor);

        return project.getId();
    }
    //Update a project

    //Add stages to the project

    // View Project
    public Project getProject(String Id){
        return projectRepo.findById(Id)
                .orElseThrow(() -> new ObjectNotFoundException("Project not found"));
    }

    public void saveProject(Project project){ projectRepo.save(project);}

    public List<Project> getAllProjects(){
        return projectRepo.findAll();
    }

    public List<Stage> getStageByProject(String projectId) {
        Project project = getProject(projectId);
        return project.getStages();
    }

    public List<Task> getTasksByStage(String stageId) {
        return stagesService.getTaskByStage(stageId);
    }

    // get all tasks, you can add to output stageTitle,..., so it output List<TaskDTO>
    public List<Task> getTasksByproject(String projectId) {
        List<Stage> stages = getStageByProject(projectId);
        List<Task> allTasks = new ArrayList<>();
        for (Stage stage : stages) {
            for (Task task : stage.getTasks()) {
                allTasks.add(task);
            }
        }
        return allTasks;
    }

    public List<Comment> getCommentsByStage(String stageId) {
        return stagesService.getCommentByStage(stageId);
    }

    public String uploadDoc(String projectId, String pdfName, MultipartFile pdf) throws IOException {
        Project project = getProject(projectId);
        Doccument document = new Doccument();
        document.setTitle(pdfName);
        document.setDoc(new Binary(BsonBinarySubType.BINARY, pdf.getBytes()));

        project.setDocument(document);
        projectRepo.save(project);
        return document.getId();
    }

    public Doccument getDocument (String projectId){
        Project project = getProject(projectId);
        return project.getDocument();
    }

    public String removeTaskById(String stageId, String taskId) {
        Stage stage = stagesService.getStage(stageId);
        if (!stage.checkTaskId(taskId))
            throw new ObjectNotFoundException("Task with Id: "+" " + taskId + " not found in stage " +" "+ stageId);

        stage.removeTaskById(taskId);
        stagesService.saveStage(stage);
        return "Taks:" +" "+"'"+ taskId + "'"+" " + "is removed";
    }

    public String removeTaskById2(String projectId, String taskId) {
        List<Stage> stages = getStageByProject(projectId);
        for (Stage stage : stages){
            for (Task task : stage.getTasks()){
                if (task.getId().equals(taskId)){
                    stage.removeTaskById(taskId);
                    stagesService.saveStage(stage);
                    return "Taks:" +" "+"'"+ taskId + "'"+" " + "is removed";
                }
            }
        }
       throw new ObjectNotFoundException("Task not found");
    }

    public String deleteProject(String projectId) {
        Project project = getProject(projectId);
        project.setStages(new ArrayList<>());
        projectRepo.delete(project);
        return "Project:" +" "+"'"+ projectId + "'"+" " + "is removed";
    }

    public Stage getStage(String stageId) {
        return stagesService.getStage(stageId);
    }


    // get Task by ID

    //update a project

    //Delete project - delete stages - delete tasks
}

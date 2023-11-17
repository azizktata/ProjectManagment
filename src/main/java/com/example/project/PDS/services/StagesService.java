package com.example.project.PDS.services;

import com.example.project.PDS.DTO.taskDTO;
import com.example.project.PDS.models.Project;
import com.example.project.PDS.models.Stage;
import com.example.project.PDS.models.Student;
import com.example.project.PDS.models.Task;
import com.example.project.PDS.repository.ProjectRepo;
import com.example.project.PDS.repository.StagesRepo;
import com.example.project.PDS.repository.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StagesService {
    private final StagesRepo stagesRepo;
    private final ProjectRepo projectRepo;
    private final StudentRepo studentRepo;

    //Add a stage
    public String createStage(String projectId){
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("project not found"));;
        Stage newStage = new Stage(
                "first Stage"
        );

        newStage.setProject(project);
        Stage stage = stagesRepo.save(newStage);

        project.addStage(stage);
        projectRepo.save(project);

        return stage.getId();
    }
    //Update a stage
    //Remove a stage
    //View a stage
    public Stage viewStage(String Id){
        return stagesRepo.findById(Id).get();
    }
    //// entering a student ID
        //Add task to stages
    public String addTask (String studentId, String stageId,taskDTO taskDTO){
        Student student = studentRepo.findById(studentId).get();
        Stage stage = stagesRepo.findById(stageId).get();

        Task task = new Task(
            taskDTO.title,taskDTO.description, student.getName()
        );

        stage.addTask(task);
        return stagesRepo.save(stage).getId();
    }

    public Stage addComment (String stageId,String comment){
        Stage stage = stagesRepo.findById(stageId).get();
        stage.addComments(comment);
        return stagesRepo.save(stage);
    }

    public Stage getStage(String stageId) {
        return stagesRepo.findById(stageId).get();
    }
    //Update a task
        //Delete a task

    //// entering supervisor ID
        //Add a comment
        //Delete a comment

}

package com.example.project.PDS.services;

import com.example.project.PDS.DTO.commentDTO;
import com.example.project.PDS.DTO.stageDTO;
import com.example.project.PDS.DTO.taskDTO;
import com.example.project.PDS.Exceptions.ListNotRemovedException;
import com.example.project.PDS.Exceptions.ObjectNotFoundException;
import com.example.project.PDS.models.*;
import com.example.project.PDS.repository.ProjectRepo;
import com.example.project.PDS.repository.StagesRepo;
import com.example.project.PDS.repository.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StagesService {
    private final StagesRepo stagesRepo;
    private final ProjectRepo projectRepo;
    private final StudentRepo studentRepo;

    //Add a stage
    public String createStage(String projectId, stageDTO stageDto){
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ObjectNotFoundException("project not found"));
        Stage newStage = new Stage(
                stageDto.getTitle()
        );

        newStage.setProject(project);
        Stage stage = stagesRepo.save(newStage);

        project.addStage(stage);
        projectRepo.save(project);

        return stage.getId();
    }

    public Stage getStage(String stageId) {
        return stagesRepo.findById(stageId)
                .orElseThrow(() -> new ObjectNotFoundException("Stage not found"));
    }

    //Remove a stage
    public String deleteStage(String stageId){
        Stage stage = getStage(stageId);
        if (!stage.getTasks().isEmpty())
            throw new ListNotRemovedException("Stage Must Be EMPTY");
        stage.setComments(new ArrayList<>());
        stagesRepo.delete(stage);
        return "Stage:" +" "+"'"+ stage.getName() + "'"+" " + "is removed";
    }

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

    public Stage addComment (String stageId, commentDTO commentDto){
        Stage stage = getStage(stageId);
        Comment comment = new Comment(commentDto.content);
        stage.addComments(comment);
        return stagesRepo.save(stage);
    }

    public void saveStage(Stage stage){
        stagesRepo.save(stage);
    }

    public List<Task> getTaskByStage(String stageId) {
        Stage stage = getStage(stageId);
        return stage.getTasks();
    }

    public List<Comment> getCommentByStage(String stageId) {
        Stage stage = getStage(stageId);
        return stage.getComments();
    }


}

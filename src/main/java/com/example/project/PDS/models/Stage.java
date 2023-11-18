package com.example.project.PDS.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private List<Comment> comments;

    @DBRef(lazy = false)
    @JsonIgnore
    private Project project;

    public Stage(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public boolean checkTask(String title){
        for(Task task : this.tasks){
            if (task.getTitle().equals(title))
                    return true;
        }
        return false;
    }

    public void removeTask(String title){
        this.tasks.removeIf(task -> task.getTitle().equals(title));
    }

    public boolean checkTaskId(String Id){
        for(Task task : this.tasks){
            if (task.getId().equals(Id))
                return true;
        }
        return false;
    }
    public void removeTaskById(String Id){
        this.tasks.removeIf(task -> task.getId().equals(Id));
    }


    public void addComments(Comment comment){
        comments.add(comment);
    }

    public boolean checkCommentId(String commentId) {
        for(Comment comment : this.comments){
            if (comment.getId().equals(commentId))
                return true;
        }
        return false;
    }

    public void removeCommentById(String commentId) {
        this.comments.removeIf(task -> task.getId().equals(commentId));
    }
}
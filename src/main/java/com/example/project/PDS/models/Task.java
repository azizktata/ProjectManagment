package com.example.project.PDS.models;

import com.example.project.PDS.Enum.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    private String id;

    private String title;
    private String description;
    private TaskStatus status;
    private String createdby;

    public Task(String title, String description, String createdby) {
        this.title = title;
        this.description = description;
        this.createdby = createdby;
    }
}

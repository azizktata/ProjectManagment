package com.example.project.PDS.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Comment {
    private String Id;
    private String content;

    public Comment() {
        this.Id = UUID.randomUUID().toString();
    }

    public Comment(String content) {
        this.content = content;
        this.Id = UUID.randomUUID().toString();
    }
}

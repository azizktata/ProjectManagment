package com.example.project.PDS.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doccument {
    @Id
    private String id;

    private String title;
    private String specification;
    private String classdiagram;
    private String usecase;

}

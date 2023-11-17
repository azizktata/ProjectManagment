package com.example.project.PDS.models;

import com.example.project.PDS.Enum.roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private String name;
    private String email;
    private String role;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
        this.role = "student";
    }
}


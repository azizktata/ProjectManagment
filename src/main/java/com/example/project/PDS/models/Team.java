package com.example.project.PDS.models;

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


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    private String name;

//    @DBRef
//    private Supervisor supervisorId;

    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void removeMember(String email){
        this.members.removeIf(member -> member.getEmail().equals(email));
    }
}
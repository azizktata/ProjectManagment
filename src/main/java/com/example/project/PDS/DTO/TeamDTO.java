package com.example.project.PDS.DTO;

import com.example.project.PDS.models.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamDTO {
    public Team team;
    public String projectTitle;
}

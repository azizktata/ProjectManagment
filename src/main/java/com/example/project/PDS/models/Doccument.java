package com.example.project.PDS.models;

import com.example.project.PDS.Enum.DocStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Doccument {
    @Id
    private String Id;

    private String title;
    private Binary doc;
    private DocStatus docStatus = DocStatus.inProcess;

    public Doccument() {
        this.Id = UUID.randomUUID().toString();
    }
}

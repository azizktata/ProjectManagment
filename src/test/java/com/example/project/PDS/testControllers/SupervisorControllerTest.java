package com.example.project.PDS.testControllers;

import com.example.project.PDS.DTO.projectDTO;
import com.example.project.PDS.DTO.userDTO;
import com.example.project.PDS.Exceptions.ObjectNotFoundException;
import com.example.project.PDS.controllers.SupervisorController;
import com.example.project.PDS.models.Supervisor;
import com.example.project.PDS.repository.ProjectRepo;
import com.example.project.PDS.repository.StagesRepo;
import com.example.project.PDS.repository.StudentRepo;
import com.example.project.PDS.repository.SupervisorRepo;
import com.example.project.PDS.services.SupervisorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SupervisorController.class, excludeAutoConfiguration = MongoAutoConfiguration.class)
public class SupervisorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SupervisorService supervisorService;
    @MockBean
    private SupervisorRepo SupervisorRepo;
    @MockBean
    private ProjectRepo projectRepo;
    @MockBean
    private StagesRepo stagesRepo;
    @MockBean
    private StudentRepo studentRepo;




    @Test
    public void testGetSupervisor() throws Exception {
        String SupervisorId = "myid";
        Supervisor supervisor = new Supervisor( "John", "john@example.com");
        supervisor.setId(SupervisorId);

        when(supervisorService.getSupervisor(SupervisorId)).thenReturn(supervisor);
        this.mockMvc.perform(get("/api/v1/supervisors/{SupervisorId}", SupervisorId))

                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(SupervisorId))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.email").value("john@example.com"));

        verify(supervisorService, times(1)).getSupervisor(SupervisorId);
    }
    @Test
    public void testGetSupervisorNotFound() throws Exception {
        String SupervisorId = "nonexistent-id";

        when(supervisorService.getSupervisor(SupervisorId)).thenThrow(new ObjectNotFoundException("Supervisor not found"));

        mockMvc.perform(get("/api/v1/supervisors/{SupervisorId}", SupervisorId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Supervisor not found"));

        verify(supervisorService, times(1)).getSupervisor(SupervisorId);
    }
    @Test
    public void testListSupervisors() throws Exception {
        List<Supervisor> Supervisors = Arrays.asList(new Supervisor("John", "John@sesame.com"), new Supervisor("Sam", "Sam@sesame.com"));
        when(supervisorService.getAllSupervisor()).thenReturn(Supervisors);

        mockMvc.perform(get("/api/v1/supervisors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[0].email").value("John@sesame.com"))
                .andExpect(jsonPath("$[1].name").value("Sam"))
                .andExpect(jsonPath("$[1].email").value("Sam@sesame.com"));

        verify(supervisorService, times(1)).getAllSupervisor();
    }

    @Test
    public void testCreateSupervisor() throws Exception {
        userDTO userDto = new userDTO("John","John@sesame.com");
        String SupervisorId = "1";
        when(supervisorService.createSupervisor(userDto)).thenReturn(SupervisorId);

        mockMvc.perform(post("/api/v1/supervisors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(SupervisorId));

        verify(supervisorService, times(1)).createSupervisor(userDto);
    }
    @Test
    public void testUpdateSupervisor() throws Exception {
        userDTO userDto = new userDTO("Jane","Jane@sesame.com");
        when(supervisorService.updateSupervisor("myid", userDto)).thenReturn("myid");

        mockMvc.perform(put("/api/v1/supervisors/myid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("myid"));

        verify(supervisorService, times(1)).updateSupervisor("myid", userDto);
    }
    @Test
    public void testRemoveSupervisor() throws Exception {

        String SupervisorId = "myid";
        String studenName = "Jhon";
        when(supervisorService.deleteSupervisor(SupervisorId)).thenReturn("Supervisor "+" "+studenName+" "+"is deleted");

        mockMvc.perform(delete("/api/v1/supervisors/id/{supervisorId}",SupervisorId))
                .andExpect(status().isOk())
                .andExpect(content().string("Supervisor "+" "+studenName+" "+"is deleted"));

        verify(supervisorService, times(1)).deleteSupervisor(SupervisorId);
    }

    @Test
    public void testRemoveSupervisorByName() throws Exception {
        String SupervisorName = "Sarra";
        when(supervisorService.deleteSupervisorByName(SupervisorName)).thenReturn("Supervisor "+" "+SupervisorName+" "+"is deleted");

        mockMvc.perform(delete("/api/v1/supervisors/{name}", SupervisorName))
                .andExpect(status().isOk())
                .andExpect(content().string("Supervisor "+" "+SupervisorName+" "+"is deleted"));

        verify(supervisorService, times(1)).deleteSupervisorByName(SupervisorName);
    }
    @Test
    public void testCreateProject() throws Exception {
        projectDTO projectDTO = new projectDTO("job finder","find jobs","3months","team1");
        String ProjectId = "myid";
        String supervisorId = "supervisorId";
        when(supervisorService.AddProject(supervisorId,projectDTO)).thenReturn(ProjectId);

        mockMvc.perform(post("/api/v1/supervisors/{supervisorId}/projects",supervisorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(ProjectId));

        verify(supervisorService, times(1)).AddProject(supervisorId,projectDTO);
    }

}

package com.example.project.PDS.testControllers;

import com.example.project.PDS.Exceptions.ObjectNotFoundException;
import com.example.project.PDS.controllers.ProjectController;
import com.example.project.PDS.models.Project;
import com.example.project.PDS.models.Team;
import com.example.project.PDS.repository.ProjectRepo;
import com.example.project.PDS.repository.StagesRepo;
import com.example.project.PDS.repository.StudentRepo;
import com.example.project.PDS.repository.SupervisorRepo;
import com.example.project.PDS.services.ProjectService;
import com.example.project.PDS.services.StagesService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProjectController.class, excludeAutoConfiguration = MongoAutoConfiguration.class)
public class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private StagesService stagesService;

    @MockBean
    private ProjectRepo ProjectRepo;
    @MockBean
    private StudentRepo studentRepo;
    @MockBean
    private StagesRepo stagesRepo;
    @MockBean
    private SupervisorRepo supervisorRepo;




    @Test
    public void testGetProject() throws Exception {
        String projectId = "myid";
        Project project = new Project("job finder","find jobs","3months",new Team("team1"));
        project.setId(projectId);
        when(projectService.getProject(projectId)).thenReturn(project);
        this.mockMvc.perform(get("/api/v1/projects/{projectId}", projectId))

                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(projectId))
                .andExpect(jsonPath("$.title").value("job finder"))
                .andExpect(jsonPath("$.description").value("find jobs"))
                .andExpect(jsonPath("$.timeline").value("3months"))
                .andExpect(jsonPath("$.team.name").value("team1"));
//                .andExpect(jsonPath("$.team.members").value(Collections.emptyList()))
//                .andExpect(jsonPath("$.stages").value(Collections.emptyList()));


        verify(projectService, times(1)).getProject(projectId);
    }
    @Test
    public void testGetProjectNotFound() throws Exception {
        String projectId = "nonexistent-id";

        when(projectService.getProject(projectId)).thenThrow(new ObjectNotFoundException("Project not found"));

        mockMvc.perform(get("/api/v1/projects/{projectId}", projectId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Project not found"));

        verify(projectService, times(1)).getProject(projectId);
    }
    @Test
    public void testListProjects() throws Exception {
        List<Project> Projects = Arrays.asList(new Project("job finder","find jobs","3months",new Team("team1")),
                new Project("capooling","cap","2months",new Team("team2")));
        when(projectService.getAllProjects()).thenReturn(Projects);

        mockMvc.perform(get("/api/v1/projects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("job finder"))
                .andExpect(jsonPath("$[0].description").value("find jobs"))
                .andExpect(jsonPath("$[0].timeline").value("3months"))
                .andExpect(jsonPath("$[0].team.name").value("team1"))

                .andExpect(jsonPath("$[1].title").value("capooling"))
                .andExpect(jsonPath("$[1].description").value("cap"))
                .andExpect(jsonPath("$[1].timeline").value("2months"))
                .andExpect(jsonPath("$[1].team.name").value("team2"));

        verify(projectService, times(1)).getAllProjects();
    }

    @Test
    public void testRemoveProject() throws Exception {
        String projectId = "myid";

        when(projectService.deleteProject(projectId)).thenReturn("Project:" +" "+"'"+ projectId + "'"+" " + "is removed");

        mockMvc.perform(delete("/api/v1/projects/{projectId}",projectId))
                .andExpect(status().isOk())
                .andExpect(content().string("Project:" +" "+"'"+ projectId + "'"+" " + "is removed"));

        verify(projectService, times(1)).deleteProject(projectId);
    }

//

}

package com.example.project.PDS.testControllers;

import com.example.project.PDS.DTO.userDTO;
import com.example.project.PDS.Exceptions.ObjectNotFoundException;
import com.example.project.PDS.controllers.StudentController;
import com.example.project.PDS.models.Student;
import com.example.project.PDS.repository.ProjectRepo;
import com.example.project.PDS.repository.StagesRepo;
import com.example.project.PDS.repository.StudentRepo;
import com.example.project.PDS.repository.SupervisorRepo;
import com.example.project.PDS.services.StudentService;
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
@WebMvcTest(controllers = StudentController.class, excludeAutoConfiguration = MongoAutoConfiguration.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;
    @MockBean
    private StudentRepo studentRepo;
    @MockBean
    private ProjectRepo projectRepo;
    @MockBean
    private StagesRepo stagesRepo;
    @MockBean
    private SupervisorRepo supervisorRepo;




    @Test
    public void testGetStudent() throws Exception {
        String studentId = "myid";
        Student student = new Student( "John", "john@example.com");
        student.setId(studentId);

        when(studentService.getStudent(studentId)).thenReturn(student);
        this.mockMvc.perform(get("/api/v1/students/{studentId}", studentId))

                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(studentId))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.email").value("john@example.com"));

        verify(studentService, times(1)).getStudent(studentId);
    }
    @Test
    public void testGetStudentNotFound() throws Exception {
        String studentId = "nonexistent-id";

        when(studentService.getStudent(studentId)).thenThrow(new ObjectNotFoundException("Student not found"));

        mockMvc.perform(get("/api/v1/students/{studentId}", studentId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Student not found"));

        verify(studentService, times(1)).getStudent(studentId);
    }
    @Test
    public void testListStudents() throws Exception {
        List<Student> students = Arrays.asList(new Student("John", "John@sesame.com"), new Student("Sam", "Sam@sesame.com"));
        when(studentService.getAllStudent()).thenReturn(students);

        mockMvc.perform(get("/api/v1/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[0].email").value("John@sesame.com"))
                .andExpect(jsonPath("$[1].name").value("Sam"))
                .andExpect(jsonPath("$[1].email").value("Sam@sesame.com"));

        verify(studentService, times(1)).getAllStudent();
    }

    @Test
    public void testCreateStudent() throws Exception {
        userDTO userDto = new userDTO("John","John@sesame.com");
        String studentId = "1";
        when(studentService.addStudent(userDto)).thenReturn(studentId);

        mockMvc.perform(post("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(studentId));

        verify(studentService, times(1)).addStudent(userDto);
    }
    @Test
    public void testUpdateStudent() throws Exception {
        userDTO userDto = new userDTO("Jane","Jane@sesame.com");
        when(studentService.updateStudent("myid", userDto)).thenReturn("myid");

        mockMvc.perform(put("/api/v1/students/myid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("myid"));

        verify(studentService, times(1)).updateStudent("myid", userDto);
    }
    @Test
    public void testRemoveStudent() throws Exception {
//
//        userDTO userDto = new userDTO("John","John@sesame.com");
//        String studentId = studentService.addStudent(userDto);
        String studentId = "myid";
        String studenName = "Jhon";
        when(studentService.deleteStudent(studentId)).thenReturn("student "+" "+studenName+" "+"is deleted");

        mockMvc.perform(delete("/api/v1/students/id/{studentId}",studentId))
                .andExpect(status().isOk())
                .andExpect(content().string("student "+" "+studenName+" "+"is deleted"));

        verify(studentService, times(1)).deleteStudent(studentId);
    }

    @Test
    public void testRemoveStudentByName() throws Exception {
        String studentName = "Sarra";
        when(studentService.deleteStudentByName(studentName)).thenReturn("student "+" "+studentName+" "+"is deleted");

        mockMvc.perform(delete("/api/v1/students/{name}", studentName))
                .andExpect(status().isOk())
                .andExpect(content().string("student "+" "+studentName+" "+"is deleted"));

        verify(studentService, times(1)).deleteStudentByName(studentName);
    }

}

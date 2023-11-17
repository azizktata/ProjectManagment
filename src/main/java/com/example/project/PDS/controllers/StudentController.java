package com.example.project.PDS.controllers;

import com.example.project.PDS.DTO.taskDTO;
import com.example.project.PDS.DTO.userDTO;
import com.example.project.PDS.models.Project;
import com.example.project.PDS.models.Student;
import com.example.project.PDS.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController(value = "/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping()
    public List<Student> getAllStudents(){return studentService.getAllStudent();}

    @GetMapping(value ="/{Id}")
    public Student getStudent(@PathVariable String Id){return studentService.getStudent(Id);}

    @PostMapping()
    public String addStudent(@RequestBody userDTO userDto){return studentService.addStudent(userDto);}

    @DeleteMapping(value ="/{Id}")
    public String removeStudent(@PathVariable String Id){return studentService.deleteStudent(Id);}

    // Enroll in project
    @PostMapping(value ="/{Id}/project/{projectId}")
    public String enrollProject(@PathVariable String Id,@PathVariable String projectId) {return studentService.EnrollProject(Id,projectId);}

    // Add task to stage
    @PostMapping(value ="/{Id}/stages/{stageId}")
    public String addTaskProject(@PathVariable String Id,@PathVariable String stageId,@RequestBody taskDTO taskDto) {return studentService.createTask(Id,stageId,taskDto);}

    // view his project
    @GetMapping(value ="/{Id}")
    public Project getStudentProject(@PathVariable String Id){return studentService.getMyProjects(Id);}


    // view his team

    // change team

    // leave team-project


}

package com.example.project.PDS.controllers;
import com.example.project.PDS.DTO.taskDTO;
import com.example.project.PDS.DTO.userDTO;
import com.example.project.PDS.models.*;
import com.example.project.PDS.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/v1/students")

public class StudentController {
    private final StudentService studentService;

    @Operation(summary = "Get All Students")
    @GetMapping()
    public List<Student> listStudents(){return studentService.getAllStudent();}

    @Operation(summary = "Get Student By Id")
    @GetMapping(value ="/{studentId}")
    public Student getStudent(@PathVariable String studentId){return studentService.getStudent(studentId);}

    @Operation(summary = "Add Student")
    @PostMapping()
    public String createStudent(@RequestBody userDTO userDto){return studentService.addStudent(userDto);}

    @Operation(summary = "Update Student")
    @PutMapping(value = "/{studentId}")
    public String updateStudent(@PathVariable String studentId,@RequestBody userDTO userDto){return studentService.updateStudent(studentId,userDto);}

    @Operation(summary = "Delete a student By Id")
    @DeleteMapping(value ="/{studentId}")
    public String removeStudent(@PathVariable String studentId){return studentService.deleteStudent(studentId);}

    // Enroll in project
    @Operation(summary = "Enroll in Project")
    @PostMapping(value ="/{studentId}/projects/{projectId}")
    public String enrollInProject(@PathVariable String studentId,@PathVariable String projectId) {return studentService.EnrollProject(studentId,projectId);}

    // Add task to stage
    @Operation(summary = "Add Task to stage / only students can add tasks")
    @PostMapping(value ="/{studentId}/stages/{stageId}/tasks")
    public String addTaskStage(@PathVariable String studentId,@PathVariable String stageId,@RequestBody taskDTO taskDto) {return studentService.createTask(studentId,stageId,taskDto);}


//    // remove task by title
//    @DeleteMapping(value ="/stages/{stageId}/tasks/{taskTitle}")
//    public String removeTaskStage(@PathVariable String stageId,@PathVariable String taskTitle){return studentService.removeTask(stageId,taskTitle);}

    @Operation(summary = "View My Project")
    @GetMapping(value ="/{studentId}/project")
    public Project getStudentProject(@PathVariable String studentId){return studentService.getMyProject(studentId);}

    @Operation(summary = "View My Team")
    @GetMapping(value ="/{studentId}/team")
    public Team getStudentTeam(@PathVariable String studentId){return studentService.getMyTeam(studentId);}

    @Operation(summary = "View My Tasks")
    @GetMapping(value ="/{studentId}/tasks")
    public List<Task> getStudentTasks(@PathVariable String studentId){return studentService.getMyTasks(studentId);}

    @Operation(summary = "View The Stages")
    @GetMapping(value ="/{studentId}/stages")
    public List<Stage> getStudentStages(@PathVariable String studentId){return studentService.getStages(studentId);}


    // leave team-project
    @Operation(summary = "Leaves the project or team")
    @DeleteMapping(value = "/{studentId}/projects/{projectId}")
    public String leaveMyProject(@PathVariable String studentId,@PathVariable String projectId) {return studentService.leaveProject(studentId,projectId);}




}

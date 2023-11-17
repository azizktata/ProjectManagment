package com.example.project.PDS.services;

import com.example.project.PDS.DTO.taskDTO;
import com.example.project.PDS.DTO.userDTO;
import com.example.project.PDS.models.*;
import com.example.project.PDS.repository.ProjectRepo;
import com.example.project.PDS.repository.StudentRepo;
import com.example.project.PDS.repository.SupervisorRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepo studentRepo;
    private final ProjectRepo projectRepo;
    private final StagesService stagesService;
    private final SupervisorRepo supervisorRepo;

    // Create a student
    public String addStudent(userDTO user){
        Student student = new Student(
                user.name, user.email
        );
        return studentRepo.save(student).getId();
    }

    // Update a student
    public void UpdateStudent(String Id, String Name, String Email){
        Student student = studentRepo.findById(Id).get();
        student.setName(Name);
        student.setEmail(Email);
        studentRepo.save(student);
    }
    // Delete a student
    public String deleteStudent(String Id){
        Student student = studentRepo.findById(Id).get();
        studentRepo.delete(student);
        return "student "+student.getName()+" is deleted";
    }

    // Select project - Add student to team
    public String EnrollProject(String studentId, String projectId){
        Student student = studentRepo.findById(studentId).get();
        Project project = projectRepo.findById(projectId).get();
        Team team = project.getTeam();

        Member member = new Member(student.getName(),student.getEmail());
        team.getMembers().add(member);

        student.setTeam(team);
        project.setTeam(team);
        student.setProject(project);
        studentRepo.save(student);
        projectRepo.save(project);
        return student.getName() + "Enrolled in project" + project.getTitle();
    }
    // Leave the team -> leave project
    public void LeaveProject(String studentId){
        //team service, leave team
        //project service, leave project
    }

    // View Team members
    public Team viewMyTeam(String studentId){
        Student student = studentRepo.findById(studentId).get();
        return student.getTeam();
    }

    // View Project (stages - tasks - timeline)
    public Project viewMyProject(String studentId){
        Student student = studentRepo.findById(studentId).get();
        return student.getProject();
    }

    public Student getStudent(String Id) {
        return studentRepo.findById(Id).get();
    }
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    public void addStage(String projectId) {
        stagesService.createStage(projectId);
    }

    public String createTask(String Id,String stageId,taskDTO taskDto){
        return stagesService.addTask(Id,stageId,taskDto);
    }

    // Delete stage

    // Create a task in a stage in a project //project service
    // Update task
    // Delete a task


    // submit the project's document
}

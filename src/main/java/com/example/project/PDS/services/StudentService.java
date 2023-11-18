package com.example.project.PDS.services;

import com.example.project.PDS.DTO.stageDTO;
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
    public Student getStudent(String Id) {
        return studentRepo.findById(Id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }
    // Update a student
    public void UpdateStudent(String Id, String Name, String Email){
        Student student = studentRepo.findById(Id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(Name);
        student.setEmail(Email);
        studentRepo.save(student);
    }
    // Delete a student
    public String deleteStudent(String Id){
        Student student = studentRepo.findById(Id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepo.delete(student);
        return "student "+student.getName()+" is deleted";
    }

    // Select project - Add student to team
    public String EnrollProject(String studentId, String projectId){
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
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
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return student.getTeam();
    }

    // View Project (stages - tasks - timeline)
    public Project viewMyProject(String studentId){
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return student.getProject();
    }



//    public String addStage(String projectId, stageDTO stageDTO) {
//        stagesService.createStage(projectId, stageDTO);
//        return "Stage " +" "+ stageDTO.title + " " +"was created";
//    }

    public String createTask(String Id,String stageId,taskDTO taskDto){
        return stagesService.addTask(Id,stageId,taskDto);
    }

    public String leaveProject(String Id, String projectId) {
        Student student = studentRepo.findById(Id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        student.getOutTeam();
        Team team = project.getTeam();
        team.removeMember(student.getEmail());
        project.setTeam(team);

        projectRepo.save(project);
        return "student "+ student.getName() + "has left project:" +project.getTitle();
    }

    public String removeTask(String stageId, String taskTitle) {
        Stage stage = stagesService.getStage(stageId);
        if (!stage.checkTask(taskTitle))
            throw new IllegalArgumentException("Task with title " + taskTitle + " not found in stage " + stageId);

        stage.removeTask(taskTitle);
        stagesService.saveStage(stage);
        return "Taks:" +" "+"'"+ taskTitle + "'"+" " + "is removed";
    }

    public String removeTaskById(String stageId, String taskId) {
        Stage stage = stagesService.getStage(stageId);
        if (!stage.checkTaskId(taskId))
            throw new IllegalArgumentException("Task with title " + taskId + " not found in stage " + stageId);

        stage.removeTaskById(taskId);
        stagesService.saveStage(stage);
        return "Taks:" +" "+"'"+ taskId + "'"+" " + "is removed";
    }

    public Project getMyProjects(String studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return student.getProject();
    }

    public Team getMyTeam(String studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return student.getTeam();
    }

    // Delete stage

    // Create a task in a stage in a project //project service
    // Update task
    // Delete a task


    // submit the project's document
}

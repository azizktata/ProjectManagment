package com.example.project.PDS;

import com.example.project.PDS.DTO.projectDTO;
import com.example.project.PDS.DTO.taskDTO;
import com.example.project.PDS.DTO.userDTO;
import com.example.project.PDS.models.*;
import com.example.project.PDS.repository.ProjectRepo;
import com.example.project.PDS.repository.StudentRepo;
import com.example.project.PDS.repository.SupervisorRepo;
import com.example.project.PDS.services.ProjectService;
import com.example.project.PDS.services.StagesService;
import com.example.project.PDS.services.StudentService;
import com.example.project.PDS.services.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class PdsProjectApplication implements CommandLineRunner {

	@Autowired
	StudentService studentService;

	@Autowired
	ProjectService projectService;

	@Autowired
	SupervisorService supervisorService;

	@Autowired
	StagesService stageService;

	public static void main(String[] args) {
		SpringApplication.run(PdsProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	//Stage 1
		//Creation users
//		userDTO user1 = new userDTO("Hajer","Hajer@sesame.com.tn");
//		userDTO user2 = new userDTO("Aziz","Aziz@sesame.com.tn");
//		supervisorService.createSupervisor(user1);
//		studentService.AddStudent(user2);
//


//	//stage 2: supervisor create a project
//		Supervisor supervisor = supervisorService.getSupervisor("6554e8d00011a0190449fd2f");
//		// Create a project
//		projectDTO newProject = new projectDTO("ProjectManagment",
//				"Platform is designed to facilitate the management",
//				"2 months"
//		);
//		supervisorService.AddProject(supervisor.getId(),newProject);

	//stage 3: Student Enroll in a project
//		Student student = studentService.getStudent("6554e8d00011a0190449fd30");
//		Project project = projectService.getProject("6554e8ed617f0373c59053ce");
//		studentService.EnrollProject(student.getId(),project.getId());

	//Stage4: add stage
//		Project project = projectService.getProject("6554e8ed617f0373c59053ce");
//		stageService.createStage(project.getId());
	//Stage5: add task to that stage
//		Stage stage = stageService.getStage("655612ebf214f9193af0fad8");
//		Student student = studentService.getStudent("6554e8d00011a0190449fd30");
//		taskDTO taskDto = new taskDTO(
//				"launch database server",
//				"create docker-compose file for mongo & mongo-express"
//		);
//		studentService.createTask(student.getId(),stage.getId(),taskDto);

		//stage6: add comment to stage
//		Stage stage = stageService.getStage("655612ebf214f9193af0fad8");
//		supervisorService.addComment(stage.getId(),"Good work! keep up the pace ;)");

		//Stage 7: Student Leave - Change Team





	}
}

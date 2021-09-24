package edu.tlabs.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TaskController {

	@GetMapping("/tasks")
	public ResponseEntity<String> getTasks() {

		System.out.println("Get tasks hit!!!!!");
				
		return new ResponseEntity<String>("TESTTTT World",HttpStatus.OK);
	}

}

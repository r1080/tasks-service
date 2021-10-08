package edu.tlabs.task.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tlabs.task.entity.Task;
import edu.tlabs.task.service.TaskService;

@RestController
public class TaskController {

	private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskService taskService;

	@Autowired
	@Qualifier("fixedThreadPoolExecutor")
	private ExecutorService executorService;

	@GetMapping("/tasks")
	public ResponseEntity<String> getTasks() {

		LOG.info("Get tasks hit!!!!!");

		return new ResponseEntity<String>("TESTTTT World", HttpStatus.OK);
	}

	@PostMapping("/tasks")
	public ResponseEntity<String> saveTask() {
		try {
			Future<String> future = taskService.saveTask();

			LOG.info("Logging thread name to check Async execution");
			LOG.info("Name {} ", Thread.currentThread().getName());

			String string = future.get();

			LOG.info("After execution {} ", string);

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Saving Task...", HttpStatus.I_AM_A_TEAPOT);
	}

	@DeleteMapping("/tasks")
	public ResponseEntity<String> deleteTask() {

		executorService.submit(() -> taskService.deleteTask());
		
		LOG.info("Main thread!!");

		return new ResponseEntity<String>("Deleting Task...", HttpStatus.I_AM_A_TEAPOT);

	}

}

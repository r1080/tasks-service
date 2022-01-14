package edu.tlabs.task.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.tlabs.task.entity.Task;
import edu.tlabs.task.entity.Tasks;
import edu.tlabs.task.filter.LogTime;
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
	public ResponseEntity<?> getTasks() {

		Future<List<Task>> future = executorService.submit(() -> taskService.findAllTasks());
		List<Task> list;
		try {
			list = future.get(5000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			LOG.error("Timeout retrieving tasks from DB ", e);
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.GATEWAY_TIMEOUT);
		}
		return new ResponseEntity<List<Task>>(list, HttpStatus.OK);
	}

	@PostMapping("/task")
	public ResponseEntity<String> saveTask(@RequestBody Task task) {
		try {

			LOG.info("Request Body Task: {} ", task);
			taskService.saveTask(task);
			LOG.info("Current Main Thread Name: {} ", Thread.currentThread().getName());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Saving Task...", HttpStatus.CREATED);
	}
	
	@PostMapping("/tasks")
	@LogTime
	public ResponseEntity<String> saveTasks(@RequestBody Tasks tasks) {
		try {

			LOG.info("Request Body Task: {} ", tasks);
			taskService.saveAllTasks(tasks.getTasks());
			LOG.info("Current Main Thread Name: {} ", Thread.currentThread().getName());
			//Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Saving Task...", HttpStatus.CREATED);
	}

	@DeleteMapping("/tasks")
	public ResponseEntity<String> deleteTask() {
		executorService.submit(() -> taskService.deleteTask());
		LOG.info("Main thread!! {} ", Thread.currentThread().getName());
		return new ResponseEntity<String>("Deleting Task...", HttpStatus.ACCEPTED);
	}

}

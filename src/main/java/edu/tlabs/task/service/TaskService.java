package edu.tlabs.task.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import edu.tlabs.task.controller.TaskRepository;

@Service
public class TaskService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	private TaskRepository taskRepository;

	@Async("asyncExecutor")
	public Future<String> saveTask() throws InterruptedException {

		LOGGER.info("Executing Asynchronous Thread -  {} ", Thread.currentThread().getName());
		Thread.sleep(10000);

		return new AsyncResult<>("Completed Execution");
	}

	public void deleteTask() {
		
		
		LOGGER.info("Executing Fixed Async Thread -  {} ", Thread.currentThread().getName());
		
		try {
			Thread.sleep(10000);
			
			LOGGER.info("Finished!");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

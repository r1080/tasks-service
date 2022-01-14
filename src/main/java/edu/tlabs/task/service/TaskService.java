package edu.tlabs.task.service;

import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import edu.tlabs.task.entity.Task;
import edu.tlabs.task.repo.TaskRepository;

@Service
public class TaskService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	private TaskRepository taskRepository;

	@Async("asyncExecutor")
	public void saveTask(Task task) throws InterruptedException {
		LOGGER.info("Executing Asynchronous Thread =>  {} ", Thread.currentThread().getName());
		taskRepository.save(task);
	}
	
	public List<Task> findAllTasks(){
		LOGGER.info("Executing Asynchronous Thread =>  {} ", Thread.currentThread().getName());
		List<Task> tasksList = taskRepository.findAll();
		return tasksList;
	}
	
	@Async("asyncExecutor")
	public void saveAllTasks(List<Task> tasks) throws InterruptedException {
		LOGGER.info("Executing Asynchronous Thread =>  {} ", Thread.currentThread().getName());
		taskRepository.saveAll(tasks);
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

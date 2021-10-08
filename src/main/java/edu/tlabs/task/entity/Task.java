package edu.tlabs.task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taskId;
	
	@Column
	private String task;
	
	@Column
	private boolean completed;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Task(int taskId, String task, boolean completed) {
		this.taskId = taskId;
		this.task = task;
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", task=" + task + ", completed=" + completed + "]";
	}

	public Task() {
		
	}

}

package edu.tlabs.task.entity;

import java.util.List;

public class Tasks {
	
	private List<Task> tasks;

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "Tasks [tasks=" + tasks + "]";
	}

	public Tasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Tasks() {
		
	}

}

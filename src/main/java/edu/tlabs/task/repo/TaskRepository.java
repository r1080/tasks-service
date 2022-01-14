package edu.tlabs.task.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.tlabs.task.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{
	

}

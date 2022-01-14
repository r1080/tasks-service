package edu.tlabs.task.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class TaskServiceConfig {

	@Bean(name = "asyncExecutor")
	public Executor ayncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setThreadNamePrefix("Async-");
		return executor;
	}
	
	@Bean(name="fixedThreadPoolExecutor")
	public ExecutorService ayncFixedExecutor() {
		ExecutorService  executorService = Executors.newFixedThreadPool(2);
		return executorService;
	}

}

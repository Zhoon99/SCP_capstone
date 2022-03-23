package kr.mmgg.scp.service;

import org.springframework.stereotype.Service;

import kr.mmgg.scp.repository.TaskRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {
	
	private TaskRepository taskRepository;
	
}

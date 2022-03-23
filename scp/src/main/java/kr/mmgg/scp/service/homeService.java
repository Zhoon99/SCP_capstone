package kr.mmgg.scp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.mmgg.scp.dto.homeView_teamleader;
import kr.mmgg.scp.dto.homeView_teammember;
import kr.mmgg.scp.repository.ProjectinUserRepository;
import kr.mmgg.scp.repository.TaskRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class homeService {
	private ProjectinUserRepository projectinUserRepository;
	private TaskRepository taskRepository;
	
	public List<homeView_teamleader> homeView_leader(Long projectId) {
		ArrayList<homeView_teamleader> list = new ArrayList<homeView_teamleader>();
		
		return list;
	}
	public List<homeView_teammember> homeView_member(Long projectId) {
		ArrayList<homeView_teammember> list = new ArrayList<homeView_teammember>();
		
		return list;
	}
	
}

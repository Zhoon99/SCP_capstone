package kr.mmgg.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
}

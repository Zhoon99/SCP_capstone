package kr.mmgg.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	public Project findByProjectId(Long projectId);
}

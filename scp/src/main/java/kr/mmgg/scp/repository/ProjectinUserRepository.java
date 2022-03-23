package kr.mmgg.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.ProjectInUser;

@Repository
public interface ProjectinUserRepository extends JpaRepository<ProjectInUser, Long> {
    public ProjectInUser findByProjectinuserId(Long id);
}

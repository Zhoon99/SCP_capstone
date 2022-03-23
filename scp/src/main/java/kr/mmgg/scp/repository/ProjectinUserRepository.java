package kr.mmgg.scp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.ProjectInUser;

@Repository
public interface ProjectinUserRepository extends JpaRepository<ProjectInUser, Long> {

    public List<ProjectInUser> findByUserId(Long id);
}

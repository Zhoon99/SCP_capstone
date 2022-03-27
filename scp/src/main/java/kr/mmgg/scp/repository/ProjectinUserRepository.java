package kr.mmgg.scp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.ProjectInUser;

@Repository
public interface ProjectinUserRepository extends JpaRepository<ProjectInUser, Long> {

    // 유저 아이디로 가져오기
    public List<ProjectInUser> findByUserId(Long id);

    // 프로젝트 아이디로 가져오기
    public List<ProjectInUser> findByProjectId(Long projectid);

    // 프로젝트 아이디와 유저아이디로 가져옴
    public ProjectInUser findByUserIdAndProjectId(Long userId, Long projectId);
    
}

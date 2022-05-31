package kr.mmgg.scp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.ProjectInUser;

@Repository
public interface ProjectinUserRepository extends JpaRepository<ProjectInUser, Long> {

    // 유저 아이디로 가져오기
    public List<ProjectInUser> findByUserId(Long id);

    // 프로젝트 아이디로 가져오기
    public List<ProjectInUser> findByProjectId(Long projectid);

    // 프로젝트 아이디와 유저아이디로 가져옴
    public Optional<ProjectInUser> findByUserIdAndProjectId(Long userId, Long projectId);
    
    
    //userId로 프로젝트Id 검색
//    public Optional<ProjectInUser> findByuserIdAnd(Long userId);
}

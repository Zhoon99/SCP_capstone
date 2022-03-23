package kr.mmgg.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.ProejectInUser;

@Repository
public interface ProjectinUserRepository extends JpaRepository<ProejectInUser, Long> {
    
}

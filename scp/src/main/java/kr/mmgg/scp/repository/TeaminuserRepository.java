package kr.mmgg.scp.repository;

import kr.mmgg.scp.entity.Team;
import kr.mmgg.scp.entity.Teaminuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeaminuserRepository extends JpaRepository<Teaminuser, Long> {
    List<Teaminuser> findTop3ByTeamId(Long teamId);
}
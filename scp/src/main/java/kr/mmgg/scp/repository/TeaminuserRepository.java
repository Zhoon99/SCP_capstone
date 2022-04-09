package kr.mmgg.scp.repository;

import kr.mmgg.scp.entity.Teaminuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeaminuserRepository extends JpaRepository<Teaminuser, Long> {

    List<Teaminuser> findTop3ByTeamIdOrderByTeaminuserCommoncodeAsc(Long teamId);

    List<Teaminuser> findByTeamIdOrderByTeaminuserCommoncodeAsc(Long teamId);

    List<Teaminuser> findByUserId(Long userId);

    List<Teaminuser> findByTeamId(Long teamId);

    void deleteByTeamId(Long teamId);
}

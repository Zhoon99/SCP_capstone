package kr.mmgg.scp.repository;

import kr.mmgg.scp.entity.Teaminuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeaminuserRepository extends JpaRepository<Teaminuser, Long> {

    List<Teaminuser> findTop3ByTeamIdOrderByTeaminuserCommoncodeAsc(Long teamId);

    List<Teaminuser> findByTeamIdOrderByTeaminuserCommoncodeAsc(Long teamId);

    List<Teaminuser> findByUserId(Long userId);

    List<Teaminuser> findByTeamId(Long teamId);

    Optional<Teaminuser> findByTeamIdAndAndUserId(@Param("teamId") Long teamId, @Param("userId") Long userId);

    void deleteByTeamId(Long teamId);
}

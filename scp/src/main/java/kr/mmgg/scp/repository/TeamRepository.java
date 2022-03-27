package kr.mmgg.scp.repository;

import kr.mmgg.scp.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    //jpa는 메소드명으로 group By 못 함
    @Query(value = "SELECT t.*\n" +
            "FROM team t\n" +
            "INNER JOIN teaminuser tu\n" +
            "ON t.team_id = tu.team_id\n" +
            "WHERE tu.user_id = :userId\n" +
            "AND tu.teaminuser_maker = 1\n" +
            "GROUP BY t.team_id", nativeQuery = true)
    List<Team> getMyTeams(Long userId);

    @Query(value = "SELECT t.*\n" +
            "FROM team t\n" +
            "INNER JOIN teaminuser tu\n" +
            "ON t.team_id = tu.team_id\n" +
            "WHERE tu.user_id = :userId\n" +
            "AND tu.teaminuser_maker = 0\n" +
            "GROUP BY t.team_id", nativeQuery = true)
    List<Team> getSharedTeams(Long userId);
}
package kr.mmgg.scp.repository;

import kr.mmgg.scp.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "SELECT t\n" +
            "FROM Team t\n" +
            "INNER JOIN Teaminuser tu\n" +
            "ON t.teamId = tu.teamId\n" +
            "WHERE tu.userId = :userId\n" +
            "AND tu.teaminuserMaker = 1")
    public List<Team> getMyTeams(Long userId);

    @Query(value = "SELECT t\n" +
            "FROM Team t\n" +
            "INNER JOIN Teaminuser tu\n" +
            "ON t.teamId = tu.teamId\n" +
            "WHERE tu.userId = :userId\n" +
            "AND tu.teaminuserMaker <> 1")
    public List<Team> getSharedTeams(Long userId);

    public Optional<Team> findByTeamId(Long teamId);
}

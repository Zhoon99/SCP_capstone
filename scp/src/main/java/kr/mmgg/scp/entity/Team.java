package kr.mmgg.scp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
//Native Query DTO 매핑
/*@SqlResultSetMapping(
        name = "getTeamMemberMapping",
        classes = @ConstructorResult(
                targetClass = TeamUserDTO.class,
                columns = {
                        @ColumnResult(name = "user_nickname", type = String.class),
                        @ColumnResult(name = "teaminuser_commoncode", type = String.class),
                })
)
@NamedNativeQuery(
        name = "getTeamMemberList",
        query = "SELECT u.user_nickname, tu.teaminuser_CommonCode\n" +
                "FROM user u\n" +
                "INNER JOIN teaminuser tu\n" +
                "ON u.user_id = tu.user_id\n" +
                "WHERE tu.team_id = :teamId\n" +
                "LIMIT 3",
        resultSetMapping = "getTeamMemberMapping")*/
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;
    private String teamName;
}

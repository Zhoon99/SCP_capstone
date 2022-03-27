package kr.mmgg.scp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Teaminuser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teaminuserId;

    private String teaminuserCommoncode;
    private Integer teaminuserMaker;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(targetEntity = Team.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "team_id")
    private Long teamId;
}

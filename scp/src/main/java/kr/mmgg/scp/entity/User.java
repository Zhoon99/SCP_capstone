package kr.mmgg.scp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.mmgg.scp.util.Provider;
import kr.mmgg.scp.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "teaminusers")
@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 20)
    private String userNickname;

    @Column(length = 255)
    private String userPassword;

    @Column(length = 255)
    private String userEmail;

    @Enumerated(EnumType.STRING)
    @Column(length = 255)
    private Provider userSnstype;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role userRole;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Teaminuser> teaminusers = new ArrayList<>();

    @Builder
    public User(String userNickname, String userEmail, Provider userSnstype, Role userRole, String userPassword) {
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userSnstype = userSnstype;
        this.userRole = userRole;
        this.userPassword = userPassword;
    }

    public User update(String nickname) {
        this.userNickname = nickname;
        return this;
    }

    public String getRoleKey() {
        return this.userRole.name();
    }
}
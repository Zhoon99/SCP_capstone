package kr.mmgg.scp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.mmgg.scp.dto.UserDto;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "user")
@ToString(exclude = "teaminusers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 20)
    private String userNickname;

    @Column(length = 255)
    private String userEmail;

    @Column(length = 255)
    private String userSnstype;

    @Column(length = 20)
    private String userRole;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Teaminuser> teaminusers = new ArrayList<>();
}
package kr.mmgg.scp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.mmgg.scp.dto.UserDto;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
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

}
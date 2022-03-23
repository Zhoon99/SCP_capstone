package kr.mmgg.scp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class UserCode {
    @Column(length = 255)
    private String commonCode;

    @Column(length = 255)
    private String parentCode;

    @Column(length = 255)
    private String commonName;

    @Column(length = 255)
    private String commonExp;

    @Column(length = 255)
    private String commonRegistertime;

}

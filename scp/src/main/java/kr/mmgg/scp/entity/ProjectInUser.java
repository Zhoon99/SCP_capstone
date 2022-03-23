package kr.mmgg.scp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "projectinuser")
public class ProjectInUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectinuserId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;


    @ManyToOne(targetEntity = Project.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id",insertable = false,updatable = false)
    private Project project;

    @Column(name="project_id")
    private Long projectId;

    @Column(length = 20)
    private String projectinuserCommoncode;

    @Column
    private Integer projectinuserMaker;

}

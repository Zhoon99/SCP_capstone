package kr.mmgg.scp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "tasks")
@Table(name = "projectinuser")
public class ProjectInUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectinuserId;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    @JsonIgnore
    @ManyToOne(targetEntity = Project.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    private Project project;

    @Column(name = "project_id")
    private Long projectId;

    @Column(length = 20)
    private String projectinuserCommoncode;

    @Column
    private Integer projectinuserMaker;
    
    @JsonIgnore
    @OneToMany(mappedBy = "projectinuser")
    private List<Task> tasks = new ArrayList<>();

}

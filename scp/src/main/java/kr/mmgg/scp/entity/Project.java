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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(length = 50)
    private String projectName;
    
    @JsonIgnore
    @ManyToOne(targetEntity = Chatroom.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id", insertable = false, updatable = false)
	private Chatroom chatroom;
	
	@Column(name = "chatroom_id")
	private Long chatroomId;
}

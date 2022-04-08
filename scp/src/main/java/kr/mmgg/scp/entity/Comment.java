package kr.mmgg.scp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	
	@JsonIgnore
    @ManyToOne(targetEntity = Task.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
	private Task task;
	
	@Column(name = "task_id")
	private Long taskId;
	
	@JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;
	
//	@Column(length = 20)
//	private String commentWriter;
	private String commentTime;
	
	@Column(length = 255)
	private String commentContent;
}

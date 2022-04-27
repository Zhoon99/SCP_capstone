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

@Entity
@Data
// @ToString(exclude = "projectinuser")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskId;

	@JsonIgnore
	@ManyToOne(targetEntity = ProjectInUser.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "projectinuser_id", insertable = false, updatable = false)
	private ProjectInUser projectinuser;

	@Column(name = "projectinuser_id")
	private Long projectinuserId;

	// @OneToMany(fetch = FetchType.LAZY)
	// @JoinTable(name = "projectinuser", joinColumns = @JoinColumn(name =
	// "projectinuser_id"))
	// private List<ProjectInUser> projectinuser;

	// private List<ProjectInUser> projectinuser = new ArrayList<>();

	@Column(length = 255)
	private String taskContent;
//	@Column(length = 20)
//	private String taskOwner;
	@Column(length = 20)
	private Long taskRequester;

	private Integer taskComplete;
	private Integer taskAccept;

	private String taskRequesttime;
	private String taskDeadline;
	private String taskCreatetime;
}

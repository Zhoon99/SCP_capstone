package kr.mmgg.scp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
// @ToString(exclude = "projectinuser")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskId;

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
	@Column(length = 20)
	private String taskOwner;
	@Column(length = 20)
	private String taskRequester;

	private Integer taskComplete;
	private Integer taskAccept;

	private String taskRequestTime;
	private String taskDeadline;
	
	private String taskCreatetime;
}

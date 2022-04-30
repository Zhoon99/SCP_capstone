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

import lombok.Data;

@Entity
@Data
@Table(name = "chatinuser")
public class ChatinUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long chatinuserId;
	
	@JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
	
	@Column(name = "user_id")
	private Long userId;
	
	@JsonIgnore
    @ManyToOne(targetEntity = Chatroom.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id", insertable = false, updatable = false)
	private Chatroom chatroom;
	
	@Column(name = "chatroom_id")
	private Long chatroomId;
  
	private String chatinuserCommoncode;
	private Integer chatinuserExit;
}

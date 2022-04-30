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
@Table(name = "message")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageId;
	
	@JsonIgnore
    @ManyToOne(targetEntity = ChatinUser.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "chatinuser_id", insertable = false, updatable = false)
	private ChatinUser chatinuser;
	
	@Column(name = "chatinuser_id")
	private Long chatinuserId;
	
	@Column(length = 255)
	private String messageContent;
	
	private String messageTime;
}

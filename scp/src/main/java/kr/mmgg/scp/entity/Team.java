package kr.mmgg.scp.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    private String teamName;
    
    @JsonIgnore
    @ManyToOne(targetEntity = Chatroom.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id", insertable = false, updatable = false)
	private Chatroom chatroom;
	
	@Column(name = "chatroom_id")
	private Long chatroomId;
}

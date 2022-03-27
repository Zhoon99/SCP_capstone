package kr.mmgg.scp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileId;
	
	private Long taskId;
	
	@Column(length = 50)
	private String fileName;
	
	private Long fileSize;
	
	@Column(length = 255)
	private String filePath;
}

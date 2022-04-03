package kr.mmgg.scp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class resultDto {
	private int status;
	private String message;
	private Object result;
}

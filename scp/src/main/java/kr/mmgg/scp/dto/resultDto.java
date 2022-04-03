package kr.mmgg.scp.dto;

import kr.mmgg.scp.util.CustomStatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultDto<T> {
	private int status;
	private String message;
	private T result;

	public void makeResult(CustomStatusCode csc, T result) {
		this.status = csc.getStatus();
		this.message = csc.getMessage();
		this.result = result;
	}
}

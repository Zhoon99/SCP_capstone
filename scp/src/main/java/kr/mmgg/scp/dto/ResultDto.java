package kr.mmgg.scp.dto;

import java.util.HashMap;
import java.util.List;

import kr.mmgg.scp.util.CustomStatusCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultDto<T> {
	private int status;
	private String message;
//	private HashMap<String, T> result;
	// private HashMap<String,List<T>> resultList;

	public ResultDto<T> makeResult(CustomStatusCode csc, T resultDto, String MapName) {
		this.status = csc.getStatus();
		this.message = csc.getMessage();

		if(resultDto != null) {
			HashMap<String, T> result = new HashMap<>();
			result.put(MapName, resultDto);
			this.result = result;
		}
		return this;
	}
	
    // private HashMap<String,List<T>> resultList;
	private HashMap<String, T> result = null;
    public ResultDto<T> makeResult(CustomStatusCode csc) {
        this.status = csc.getStatus();
        this.message = csc.getMessage();
        return this;
    }

	// public void makeResult(CustomStatusCode csc, List<T> resultDto,String
	// MapName) {
	// this.status = csc.getStatus();
	// this.message = csc.getMessage();
	// HashMap<String,List<T>> result = new HashMap<>();
	// result.put(MapName, resultDto);
	// this.resultList = result;
	// }
}

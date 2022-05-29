package kr.mmgg.scp.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class HomeViewRealDto {
	private String profileUsername;
	private List<HomeViewDto> projects;
}

package kr.mmgg.scp.dto.request;

import lombok.Data;

@Data
public class ModifyProjectAddMember {
	private Long userId;
	private String projectinuserCommoncode;
	private Integer projectinuserMaker;
}

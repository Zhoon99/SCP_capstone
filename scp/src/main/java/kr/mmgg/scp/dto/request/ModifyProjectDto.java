package kr.mmgg.scp.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class ModifyProjectDto {
	private String title;
	private List<ModifyProjectAddMember> member;
}

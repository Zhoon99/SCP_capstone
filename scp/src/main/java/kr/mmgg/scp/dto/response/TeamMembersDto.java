package kr.mmgg.scp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamMembersDto {

    private Long userId;
    private String userNickname;
    private String teaminuserCommoncode;
    private Integer teaminuserMaker;
}

package kr.mmgg.scp.dto;

import kr.mmgg.scp.entity.Teaminuser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeaminuserDto {

    private Long userId;
    private Long teamId;
    private String teaminuserCommoncode;
    private Integer teaminuserMaker;

    public Teaminuser teamDtoToEntity(TeaminuserDto teaminuserDTO) {
        return Teaminuser.builder()
                .userId(teaminuserDTO.getUserId())
                .teamId(teaminuserDTO.getTeamId())
                .teaminuserCommoncode(teaminuserDTO.getTeaminuserCommoncode())
                .teaminuserMaker(teaminuserDTO.getTeaminuserMaker())
                .build();
    }
}
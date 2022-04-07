package kr.mmgg.scp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeaminuserDto {

    private Long userId;
    private Long teamId;
    private String teaminuserCommoncode;
    private Integer teaminuserMaker;
}

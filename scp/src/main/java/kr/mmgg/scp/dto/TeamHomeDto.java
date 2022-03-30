package kr.mmgg.scp.dto;

import java.util.List;

import kr.mmgg.scp.entity.Teaminuser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamHomeDto {

    private String teamName;

    private List<Teaminuser> teaminusers;
}
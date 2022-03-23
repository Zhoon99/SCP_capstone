package kr.mmgg.scp.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UserDto {
    private Long id;
    private String userNickname;
    private String userEmail;
    private String userSnstype;

}

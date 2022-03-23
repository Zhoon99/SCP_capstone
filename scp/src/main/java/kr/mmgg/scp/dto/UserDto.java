package kr.mmgg.scp.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserDto {
    private Long id;
    private String userNickname;
    private String userEmail;
    private String userSnstype;
    private String userRole;

    public UserDto(Long id, String userNickname, String userEmail, String userSnstype, String userRole) {
        this.id = id;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userSnstype = userSnstype;
        this.userRole = userRole;
    }

}

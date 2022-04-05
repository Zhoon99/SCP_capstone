package kr.mmgg.scp.dto;

import kr.mmgg.scp.entity.User;
import lombok.*;

@ToString
@Data
public class UserDto {
    private Long id;
    private String userNickname;
    private String userEmail;
    private String userSnstype;
    private String userRole;

    public UserDto(User user) {
        this.id = user.getUserId();
        this.userNickname = user.getUserNickname();
        this.userEmail = user.getUserEmail();
        this.userSnstype = user.getUserSnstype();
        this.userRole = user.getUserRole();
    }

}

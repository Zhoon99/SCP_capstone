package kr.mmgg.scp.dto;

import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.util.Provider;
import kr.mmgg.scp.util.Role;
import lombok.*;

@ToString
@Data
public class UserDto {
    private String userNickname;
    private Long userId;
    private String userEmail;
    private String userPassword;

    public UserDto(User user) {
        this.userNickname = user.getUserNickname();
        this.userEmail = user.getUserEmail();
        this.userPassword = user.getUserPassword();
        this.userId = user.getUserId();
    }

}

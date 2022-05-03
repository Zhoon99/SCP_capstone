package kr.mmgg.scp.config.oauth2;

import java.io.Serializable;

import kr.mmgg.scp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
    private String userEmail;
    private String userNickname;
    private String userSnstype;

    public SessionUser(User user) {
        this.userEmail = user.getUserEmail();
        this.userNickname = user.getUserNickname();
        this.userSnstype = user.getUserSnstype();
    }
}

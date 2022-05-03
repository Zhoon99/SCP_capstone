package kr.mmgg.scp.config.oauth2;

import java.util.Map;

import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.util.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameArrtributKey;
    private String userNickname;
    private String userEmail;
    private String userSnstype;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameArrtributKey, String userNickname,
            String userEmail, String userSnstype) {
        this.attributes = attributes;
        this.nameArrtributKey = nameArrtributKey;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userSnstype = userSnstype;
    }

    public static OAuthAttributes of(String registratiold, String userNameAttributeName,
            Map<String, Object> attribute) {
        return ofGoogle(userNameAttributeName, attribute);
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .userNickname(String.valueOf(attributes.get("userNickname")))
                .userEmail(String.valueOf(attributes.get("userEmail")))
                .userSnstype(String.valueOf(attributes.get("userSnstype")))
                .attributes(attributes)
                .nameArrtributKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .userEmail(userEmail)
                .userNickname(userNickname)
                .userRole(Role.USER)
                .userSnstype(userSnstype)
                .build();
    }
}
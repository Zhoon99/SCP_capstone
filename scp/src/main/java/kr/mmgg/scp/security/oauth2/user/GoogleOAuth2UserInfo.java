package kr.mmgg.scp.security.oauth2.user;

import java.util.Map;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getUserId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getUserNickname() {
        return (String) attributes.get("name");
    }

    @Override
    public String getUserEmail() {
        return (String) attributes.get("email");
    }

}

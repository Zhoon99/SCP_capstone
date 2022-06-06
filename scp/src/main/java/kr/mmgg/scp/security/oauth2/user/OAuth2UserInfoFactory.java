package kr.mmgg.scp.security.oauth2.user;

import java.util.Map;

import kr.mmgg.scp.util.CustomException;
import kr.mmgg.scp.util.ErrorCode;
import kr.mmgg.scp.util.Provider;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(Provider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new CustomException(ErrorCode.PAGE_NOT_FOUND);
        }
    }
}

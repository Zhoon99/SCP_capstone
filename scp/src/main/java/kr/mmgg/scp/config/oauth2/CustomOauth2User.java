package kr.mmgg.scp.config.oauth2;

import org.springframework.security.oauth2.core.user.OAuth2User;

public interface CustomOauth2User extends OAuth2User {
    String getEmail();

    String getNickname();

    String getSystype();

}

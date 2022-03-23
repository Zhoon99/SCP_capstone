package kr.mmgg.scp.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import kr.mmgg.scp.entity.User;

public class PrincipalDetails implements OAuth2User {
    private User user;
    private Map<String, Object> attributes;

    // Oauth 로그인 처리
    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    // 해당 유저의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                // TODO Auto-generated method stub
                return user.getUserRole();
            }
        });
        return collect;
    }

    @Override
    public Map<String, Object> getAttributes() {
        // TODO Auto-generated method stub
        return attributes;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return attributes.get("sub").toString();
    }
}

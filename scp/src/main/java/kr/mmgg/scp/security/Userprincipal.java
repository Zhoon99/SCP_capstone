package kr.mmgg.scp.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import kr.mmgg.scp.entity.User;
import lombok.Getter;

@Getter
public class Userprincipal implements OAuth2User, UserDetails {

    private Long userId;
    private String userEmail;
    private String userPassword;

    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public Userprincipal(Long userId, String userEmail, String userPassword,
            Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.authorities = authorities;
    }

    public static Userprincipal create(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new Userprincipal(user.getUserId(), user.getUserEmail(), user.getUserPassword(), authorities);
    }

    public static Userprincipal create(User user, Map<String, Object> attributes) {
        Userprincipal userprincipal = Userprincipal.create(user);
        userprincipal.setAttributes(attributes);
        return userprincipal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return String.valueOf(userId);
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

}

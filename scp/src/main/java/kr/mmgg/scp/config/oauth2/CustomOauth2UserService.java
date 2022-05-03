package kr.mmgg.scp.config.oauth2;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration.ProviderDetails.UserInfoEndpoint;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.UserRepository;
import kr.mmgg.scp.util.Role;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 로그인 진행중인 서비스 구분
        String registratiold = userRequest.getClientRegistration().getRegistrationId();

        // oauth2 로그인 시 키가 되는 필드
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registratiold, userNameAttributeName,
                oAuth2User.getAttributes());

        User authUser = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(authUser));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(authUser.getRoleKey())),
                attributes.getAttributes(), attributes.getNameArrtributKey());

    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByUserEmail(attributes.getUserEmail())
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }

}
package kr.mmgg.scp.security.oauth2;

import java.util.Optional;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.UserRepository;
import kr.mmgg.scp.security.Userprincipal;
import kr.mmgg.scp.security.oauth2.user.OAuth2UserInfo;
import kr.mmgg.scp.security.oauth2.user.OAuth2UserInfoFactory;
import kr.mmgg.scp.util.CustomException;
import kr.mmgg.scp.util.ErrorCode;
import kr.mmgg.scp.util.Provider;
import kr.mmgg.scp.util.Role;
import lombok.RequiredArgsConstructor;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try {
            return processOauth2User(userRequest, oAuth2User);
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    // 사용자 정보 추출
    private OAuth2User processOauth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory
                .getOAuth2UserInfo(userRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (!StringUtils.hasText(oAuth2UserInfo.getUserEmail())) {
            // OAuth 공급자에서 이메일을 찾을 수 없습니다.
            throw new CustomException(ErrorCode.PAGE_NOT_FOUND);
        }

        Optional<User> userOptional = userRepository.findByUserEmail(oAuth2UserInfo.getUserEmail());
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (!user.getUserSnstype()
                    .equals(Provider.valueOf(userRequest.getClientRegistration().getRegistrationId()))) {
                // 계정 사용을 위해 로그인 해야함
                throw new CustomException(ErrorCode.PAGE_NOT_FOUND);
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(userRequest, oAuth2UserInfo);
        }

        return Userprincipal.create(user, oAuth2User.getAttributes());

    }

    // DB에 존재하지 않을 경우 새로 등록
    private User registerNewUser(OAuth2UserRequest userRequest, OAuth2UserInfo userInfo) {
        return userRepository.save(User.builder()
                .userNickname(userInfo.getUserNickname())
                .userEmail(userInfo.getUserEmail())
                .userSnstype(Provider.valueOf(userRequest.getClientRegistration().getRegistrationId()))
                .userRole(Role.ROLE_USER)
                .build());
    }

    // DB에 존재할 경우 정보 업데이트
    private User updateExistingUser(User user, OAuth2UserInfo oAuth2UserInfo) {
        return userRepository.save(user.update(oAuth2UserInfo.getUserNickname()));
    }
}

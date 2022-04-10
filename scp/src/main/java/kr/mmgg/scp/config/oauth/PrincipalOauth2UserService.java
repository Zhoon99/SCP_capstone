package kr.mmgg.scp.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import kr.mmgg.scp.config.auth.PrincipalDetails;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		OAuth2User oauth2User = super.loadUser(userRequest);

		User user = new User();
		user.setUserNickname(oauth2User.getAttribute("name"));
		user.setUserEmail(oauth2User.getAttribute("email"));
		user.setUserSnstype(userRequest.getClientRegistration().getClientName());
		user.setUserRole("ROLE_USER");

		if (userRepository.findByUserEmail(user.getUserEmail()) == null) {
			userRepository.save(user);
		}
		return new PrincipalDetails(user, oauth2User.getAttributes());
	}
}

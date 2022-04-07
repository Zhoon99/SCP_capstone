package kr.mmgg.scp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.mmgg.scp.config.auth.PrincipalDetails;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.UserRepository;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public @ResponseBody String OAuthLogin(Authentication authentication) {
        // @AuthenticationPrincipal OAuth2User oauth 세션을 이용하여 Index Controller 에서 사용가능
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal(); // 의존성 주입
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String sub = principalDetails.getAttribute("sub");
        System.out.println(sub); // sub값
        System.out.println(principalDetails.getAttribute("email").toString());
        Optional<User> user = userRepository.findByUserEmail(oauth2User.getAttribute("email"));
        return user.toString();
    }

    @GetMapping("/login")
    public String Login() {
        return "login";
    }
}
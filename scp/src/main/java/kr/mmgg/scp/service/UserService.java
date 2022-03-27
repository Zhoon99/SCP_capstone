package kr.mmgg.scp.service;

import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 이메일로 유저 id 가져오기
     */
    public Long getUserIdByEmail(String userEmail) {
        User emailUser = userRepository.findByUserEmail(userEmail);
        if(emailUser != null) {
            return emailUser.getUserId();
        } else {
            return null;
        }
    }
}

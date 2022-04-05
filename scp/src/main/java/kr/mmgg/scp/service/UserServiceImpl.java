package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * 해당 유저 정보 가져오기
     * @return
     */
    @Override
    @Transactional
    public UserDto getUserByUserId(Long userId){
        Optional<User> user = userRepository.findByUserId(userId);
        UserDto userDto = new UserDto(user.get());
        return userDto;
    }

    /**
     * 이메일로 유저 id 가져오기
     */
    @Override
    @Transactional
    public UserDto getUserIdByEmail(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail);
        if(user == null) {
            throw new IllegalArgumentException("이메일 오류");
        }
        UserDto userDto = new UserDto(user);
        return userDto;
    }
}

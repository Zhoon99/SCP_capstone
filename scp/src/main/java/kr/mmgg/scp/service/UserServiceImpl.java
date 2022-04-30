package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.entity.User;
import kr.mmgg.scp.repository.UserRepository;
import kr.mmgg.scp.util.CustomException;
import kr.mmgg.scp.util.CustomStatusCode;
import kr.mmgg.scp.util.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * 해당 유저 정보 가져오기
     */
    @Override
    @Transactional
    public ResultDto<UserDto> getUserByUserId(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        UserDto userDto = new UserDto(user);

        ResultDto<UserDto> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, userDto, "user");
        return rDto;
    }

    /**
     * 이메일로 유저 id 가져오기
     */
    @Override
    @Transactional
    public ResultDto<UserDto> getUserIdByEmail(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        UserDto userDto = new UserDto(user);

        ResultDto<UserDto> rDto = new ResultDto<>();
        rDto.makeResult(CustomStatusCode.LOOKUP_SUCCESS, userDto, "user");
        return rDto;
    }

    @Override
    public User findByUserId(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));;
        return user;
    }
}

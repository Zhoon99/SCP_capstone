package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.entity.User;

public interface UserService {

    public UserDto getUserByUserId(Long userId);

    public UserDto getUserIdByEmail(String userEmail);

    /*default UserDto toUserDto(User user) {
        UserDto userDto = UserDto.builder()
                .id(user.getUserId())
                .userNickname(user.getUserNickname())
                .userEmail(user.getUserEmail())
                .userSnstype(user.getUserSnstype())
                .userRole(user.getUserRole())
                .build();
        return userDto;
    }*/
}

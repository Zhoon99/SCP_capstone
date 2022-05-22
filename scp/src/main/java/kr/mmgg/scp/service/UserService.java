package kr.mmgg.scp.service;

import kr.mmgg.scp.dto.ResultDto;
import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.entity.User;

public interface UserService {

    public ResultDto<UserDto> getUserByUserId(Long userId);

    public ResultDto<UserDto> getUserIdByEmail(String userEmail);

    public User findByUserId(Long userId);
}

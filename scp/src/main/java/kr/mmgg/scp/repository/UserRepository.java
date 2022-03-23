package kr.mmgg.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.dto.UserDto;
import kr.mmgg.scp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserEmail(String email);
}

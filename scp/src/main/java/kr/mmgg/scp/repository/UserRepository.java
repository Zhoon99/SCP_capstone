package kr.mmgg.scp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserEmail(String email);

    public User findByUserId(Long userId);
}

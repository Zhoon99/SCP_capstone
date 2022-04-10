package kr.mmgg.scp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUserEmail(String email);

    public Optional<User> findByUserId(Long userId);

    public List<User> findByUserEmailStartingWith(String search);

}

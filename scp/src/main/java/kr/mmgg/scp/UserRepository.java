package kr.mmgg.scp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entitiy.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

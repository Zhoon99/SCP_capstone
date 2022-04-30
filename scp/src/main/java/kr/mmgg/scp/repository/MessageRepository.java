package kr.mmgg.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.Message;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

    public List<Message> findByChatinuserId(Long chatinuserId);
}

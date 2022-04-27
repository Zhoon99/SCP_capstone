package kr.mmgg.scp.repository;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.ChatinUser;

@Repository
public interface ChatinUserRepository extends JpaAttributeConverter<ChatinUser, Long>{

}

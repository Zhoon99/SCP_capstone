package kr.mmgg.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	public Comment findByCommentId(Long commentId);
}

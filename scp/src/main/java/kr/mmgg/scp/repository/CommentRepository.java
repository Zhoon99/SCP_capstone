package kr.mmgg.scp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.mmgg.scp.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	public Optional<Comment> findByCommentId(Long commentId);
	public List<Comment> findByTaskId(Long taskId);
}

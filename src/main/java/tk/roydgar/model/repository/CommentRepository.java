package tk.roydgar.model.repository;

import org.springframework.data.repository.CrudRepository;
import tk.roydgar.model.entity.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByClientId(Long client_client_id);
    Comment updateUsefulness(Integer usefulness);
}

package tk.roydgar.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tk.roydgar.model.entity.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Comment findByClientId(Long client_client_id);
}

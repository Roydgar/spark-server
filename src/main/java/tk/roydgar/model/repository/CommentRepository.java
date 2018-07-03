package tk.roydgar.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tk.roydgar.model.entity.Comment;


@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Comment findByClientId(Long client_client_id);
}

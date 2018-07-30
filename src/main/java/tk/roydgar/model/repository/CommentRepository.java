package tk.roydgar.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tk.roydgar.model.entity.comment.Comment;
import tk.roydgar.model.entity.comment.Vote;

import java.util.List;


@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllByClientIdOrderByTimeDesc(Long client_client_id);

    List<Comment> findAllByParentId(Long parentId);

    List<Comment> findAllByClientIdAndParentId(Long clientId, Long parentId);

}

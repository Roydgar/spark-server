package tk.roydgar.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tk.roydgar.model.entity.Comment;

import java.util.List;


@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllByClientId(Long client_client_id);

    List<Comment> findAllByParentId(Long parentId);

    List<Comment> findAllByClientIdAndParentId(Long clientId, Long parentId);

}

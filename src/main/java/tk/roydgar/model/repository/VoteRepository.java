package tk.roydgar.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tk.roydgar.model.entity.comment.Vote;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

    boolean existsByUserIdAndCommentId(Long userId, Long commentId);

    Optional<Vote> findByUserIdAndCommentId(Long userId, Long commentId);

    List<Vote> findAllByUserId(Long userId);

}

package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.comment.Comment;
import tk.roydgar.model.entity.client.Client;
import tk.roydgar.model.entity.comment.Vote;
import tk.roydgar.model.entity.user.User;
import tk.roydgar.model.repository.ClientRepository;
import tk.roydgar.model.repository.CommentRepository;
import tk.roydgar.model.repository.UserRepository;
import tk.roydgar.model.repository.VoteRepository;
import tk.roydgar.util.Utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static tk.roydgar.util.HttpHeadersUtil.httpHeaders;
import static tk.roydgar.util.ResponseEntityUtil.responseEntityFromList;
import static tk.roydgar.util.constants.HeaderMessages.ENTITIES_NOT_FOUND;
import static tk.roydgar.util.constants.HeaderMessages.HEADER_KEY;
import static tk.roydgar.util.constants.HeaderMessages.USER_VOTED_COMMENT;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {

    private CommentRepository commentRepository;
    private ClientRepository clientRepository;
    private UserRepository userRepository;
    private VoteRepository voteRepository;
    private Logger logger;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity<?> findByClientId(Long clientId) {
        return responseEntityFromList(commentRepository.findAllByClientIdOrderByTimeDesc(clientId));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(Comment comment, Long clientId, Long userId) {

        comment.setTime(Utils.getLocalDateTimeInUTC());

        Optional<Client> client = clientRepository.findById(clientId);
        Optional<User> user = userRepository.findById(userId);

        if (!client.isPresent() || !user.isPresent()) {
            logger.info("save() call; FAILURE; client or user not found; clientId = " + client
                +"; userId = " + userId);
            return new ResponseEntity<>(httpHeaders(HEADER_KEY, ENTITIES_NOT_FOUND), NOT_FOUND);
        }

        comment.setClient(client.get());
        comment.setUser(user.get());

        if (comment.getParentId() != 0) {
            Optional<Comment> parentComment = commentRepository.findById(comment.getParentId());

            if (!parentComment.isPresent()) {
                return new ResponseEntity<>(httpHeaders(HEADER_KEY, ENTITIES_NOT_FOUND), NOT_FOUND);
            } else {
                Comment foundedParentComment = parentComment.get();
                foundedParentComment.setReplayCount(foundedParentComment.getReplayCount() + 1);
                commentRepository.save(foundedParentComment);
            }

        }

        logger.info("save() call; SUCCESS.");
        return new ResponseEntity<>(commentRepository.save(comment), OK);
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity<?> findCommentReplays(Long parentCommentId) {
        return responseEntityFromList(
                commentRepository.findAllByParentId(parentCommentId));
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity<?> findCommentParents(Long clientId) {
        return responseEntityFromList(
                commentRepository.findAllByClientIdAndParentId(clientId, 0L));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updatePositiveRating(Long commentId, Long userId) {

        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalComment.isPresent() || ! optionalUser.isPresent()) {
            logger.info("updatePositiveRating() call; FAILURE; comment or user not found;" +
                    "commentId = " + commentId + "userId = " + userId);
            return new ResponseEntity<>(httpHeaders(HEADER_KEY, ENTITIES_NOT_FOUND), NOT_FOUND);
        }

        Comment comment = optionalComment.get();
        User user = optionalUser.get();

        Optional<Vote> vote = voteRepository.findByUserIdAndCommentId(userId, commentId);

        if (!vote.isPresent()) {
            Vote newVote = Vote.builder().user(user).comment(comment).type(Vote.TYPE.THUMB_UP).build();
            voteRepository.save(newVote);

            comment.getVotes().add(newVote);
            comment.setPositiveRating(comment.getPositiveRating() + 1);
            Comment savedComment = commentRepository.save(comment);

            logger.info("updatePositiveRating() call; SUCCESS");
            return new ResponseEntity<>(savedComment, OK);
        }

        Vote foundedVote = vote.get();

        if (foundedVote.getType() == Vote.TYPE.THUMB_UP) {
            logger.info("updatePositiveRating() call; FAILURE; User voted for this comment;" +
                    "commentId = " + commentId + "; userId = " + userId + "; type = " + foundedVote.getType());
            return new ResponseEntity<>(httpHeaders(HEADER_KEY, USER_VOTED_COMMENT), FORBIDDEN);
        }

        foundedVote.setType(Vote.TYPE.THUMB_UP);

        comment.setNegativeRating(comment.getNegativeRating() - 1);
        comment.setPositiveRating(comment.getPositiveRating() + 1);

        Comment savedComment = commentRepository.save(comment);
        logger.info("updatePositiveRating() call; SUCCESS; Changed vote");
        return new ResponseEntity<>(savedComment, OK);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateNegativeRating(Long commentId, Long userId) {

        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalComment.isPresent() || ! optionalUser.isPresent()) {
            logger.info("updateNegativeRating() call; FAILURE; comment or user not found;" +
                    "commentId = " + commentId + "userId = " + userId);
            return new ResponseEntity<>(httpHeaders(HEADER_KEY, ENTITIES_NOT_FOUND), NOT_FOUND);
        }

        Comment comment = optionalComment.get();
        User user = optionalUser.get();

        Optional<Vote> vote = voteRepository.findByUserIdAndCommentId(userId, commentId);

        if (!vote.isPresent()) {
            Vote newVote = Vote.builder().user(user).comment(comment).type(Vote.TYPE.THUMB_DOWN).build();
            voteRepository.save(newVote);

            comment.getVotes().add(newVote);
            comment.setNegativeRating(comment.getNegativeRating() + 1);
            Comment savedComment = commentRepository.save(comment);

            logger.info("updatePositiveRating() call; SUCCESS");
            return new ResponseEntity<>(savedComment, OK);
        }

        Vote foundedVote = vote.get();

        if (foundedVote.getType() == Vote.TYPE.THUMB_DOWN) {
            logger.info("updatePositiveRating() call; FAILURE; User voted for this comment;" +
                    "commentId = " + commentId + "; userId = " + userId + "; type = " + foundedVote.getType());
            return new ResponseEntity<>(httpHeaders(HEADER_KEY, USER_VOTED_COMMENT), FORBIDDEN);
        }

        foundedVote.setType(Vote.TYPE.THUMB_DOWN);

        comment.setNegativeRating(comment.getNegativeRating() + 1);
        comment.setPositiveRating(comment.getPositiveRating() - 1);

        Comment savedComment = commentRepository.save(comment);
        logger.info("updatePositiveRating() call; SUCCESS; Changed vote");
        return new ResponseEntity<>(savedComment, OK);
   }

}

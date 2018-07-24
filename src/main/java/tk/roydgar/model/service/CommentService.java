package tk.roydgar.model.service;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.Comment;
import tk.roydgar.model.entity.client.Client;
import tk.roydgar.model.entity.user.User;
import tk.roydgar.model.repository.ClientRepository;
import tk.roydgar.model.repository.CommentRepository;
import tk.roydgar.model.repository.UserRepository;
import tk.roydgar.util.Utils;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static tk.roydgar.util.ResponseEntityUtil.responseEntityFromList;
import static tk.roydgar.util.constants.HeaderMessages.HEADER_KEY;
import static tk.roydgar.util.constants.HeaderMessages.USER_VOTED_COMMENT;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {

    private CommentRepository commentRepository;
    private ClientRepository clientRepository;
    private UserRepository userRepository;
    private Logger logger;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity<?> findByClientId(Long clientId) {
        return responseEntityFromList(commentRepository.findAllByClientId(clientId));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(Comment comment, Long clientId, Long userId) {

        comment.setTime(Utils.getLocalDateTimeInUTC());

        Optional<Client> client = clientRepository.findById(clientId);
        Optional<User> user = userRepository.findById(userId);

        if (!client.isPresent() || !user.isPresent()) {
            logger.info("save() call; FAILURE; client or user not found; clientId = " + client
                +"; userId = " + userId);
            return new ResponseEntity<>(BAD_REQUEST);
        }

        comment.setClient(client.get());
        comment.setUser(user.get());

        logger.info("save() call; SUCCESS.");
        return new ResponseEntity<>(commentRepository.save(comment), OK);
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity<?> findCommentReplays(Long clientId) {
        return responseEntityFromList(
                commentRepository.findAllByClientIdAndParentIdGreaterThan(clientId, 0L));
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity<?> findCommentParents(Long clientId) {
        return responseEntityFromList(
                commentRepository.findAllByClientIdAndParentIdEquals(clientId, 0L));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updatePositiveRating(Long commentId, Long userId) {

        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalComment.isPresent() || ! optionalUser.isPresent()) {
            logger.info("updatePositiveRating() call; FAILURE; comment or user not found;" +
                    "commentId = " + commentId + "userId = " + userId);
            return new ResponseEntity<>(BAD_REQUEST);
        }

        Comment comment = optionalComment.get();
        User user = optionalUser.get();

        if (comment.getVotedUsers().contains(user)) {
            logger.info("updatePositiveRating() call; FAILURE; User voted for this comment;" +
                    "commentId = " + commentId + "userId = " + userId);
            return new ResponseEntity<>(ImmutableMap.of(HEADER_KEY, USER_VOTED_COMMENT), BAD_REQUEST);
        }

        comment.setPositiveRating(comment.getPositiveRating() + 1);
        comment.getVotedUsers().add(user);

        Comment savedComment = commentRepository.save(comment);
        logger.info("updatePositiveRating() call; SUCCESS");
        return new ResponseEntity<>(savedComment, OK);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateNegativeRating(Long commentId, Long userId) {

        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalComment.isPresent() || ! optionalUser.isPresent()) {
            logger.info("updateNegativeRating() call; FAILURE; comment or user not found;" +
                    "commentId = " + commentId + "userId = " + userId);
            return new ResponseEntity<>(BAD_REQUEST);
        }

        Comment comment = optionalComment.get();
        User user = optionalUser.get();

        if (comment.getVotedUsers().contains(user)) {
            logger.info("updateNegativeRating() call; FAILURE; User voted for this comment;" +
                    "commentId = " + commentId + "userId = " + userId);
            return new ResponseEntity<>(ImmutableMap.of(HEADER_KEY, USER_VOTED_COMMENT), BAD_REQUEST);
        }

        comment.setNegativeRating(comment.getNegativeRating() + 1);
        comment.getVotedUsers().add(user);

        Comment savedComment = commentRepository.save(comment);
        logger.info("updateNegativeRating() call; SUCCESS");
        return new ResponseEntity<>(savedComment, OK);
    }

}

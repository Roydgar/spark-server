package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {

    private CommentRepository commentRepository;
    private ClientRepository clientRepository;
    private UserRepository userRepository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Comment> findByClientId(Long clientId) {
        return commentRepository.findAllByClientId(clientId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Comment save(Comment comment, Long clientId, Long userId) {

        comment.setTime(Utils.getLocalDateTimeInUTC());

        Optional<Client> client = clientRepository.findById(clientId);
        Optional<User> user = userRepository.findById(userId);

        if (!client.isPresent() || !user.isPresent()) {
            return null;
        }

        comment.setClient(client.get());
        comment.setUser(user.get());

        return commentRepository.save(comment);
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Comment> findCommentReplays(Long clientId) {
        return commentRepository.findAllByClientIdAndParentIdGreaterThan(clientId, 0L);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Comment> findCommentParents(Long clientId) {
        return commentRepository.findAllByClientIdAndParentIdEquals(clientId, 0L);
    }

    @Transactional(rollbackFor = Exception.class)
    public Comment updatePositiveRating(Long commentId, Long userId) {

        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalComment.isPresent() || ! optionalUser.isPresent()) {
            return null;
        }

        Comment comment = optionalComment.get();
        User user = optionalUser.get();

        if (comment.getVotedUsers().contains(user)) {
            return null;
        }

        comment.setPositiveRating(comment.getPositiveRating() + 1);
        comment.getVotedUsers().add(user);

        return commentRepository.save(comment);
    }

    @Transactional(rollbackFor = Exception.class)
    public Comment updateNegativeRating(Long commentId, Long userId) {

        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalComment.isPresent() || ! optionalUser.isPresent()) {
            return null;
        }

        Comment comment = optionalComment.get();
        User user = optionalUser.get();

        if (comment.getVotedUsers().contains(user)) {
            return null;
        }

        comment.setNegativeRating(comment.getNegativeRating() + 1);
        comment.getVotedUsers().add(user);

        return commentRepository.save(comment);
    }

}

package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.Comment;
import tk.roydgar.model.repository.ClientRepository;
import tk.roydgar.model.repository.CommentRepository;
import tk.roydgar.util.Utils;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {

    private CommentRepository commentRepository;
    private ClientRepository clientRepository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Comment> findByClientId(String clientId) {
        Long id = Utils.parseId(clientId);
        if (id == null) {
            return null;
        }

        return commentRepository.findAllByClientId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Comment save(Comment comment, String clientId) {
        Long id = Utils.parseId(clientId);
        if (comment == null || id == null) {
            return null;
        }

        comment.setUsefulness(0);
        comment.setTime(Utils.getLocalDateTimeInUTC());

        return clientRepository.findById(id).map(client -> {
            comment.setClient(client);
            return commentRepository.save(comment);
        }).orElse(null);
    }

}

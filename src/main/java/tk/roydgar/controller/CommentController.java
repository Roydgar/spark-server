package tk.roydgar.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.model.entity.comment.Comment;
import tk.roydgar.model.service.CommentService;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

    private CommentService commentService;

    @GetMapping("/comments/{clientId}")
    public ResponseEntity<?> findByClientId(@PathVariable Long clientId) {
        return commentService.findByClientId(clientId);
    }

    @PostMapping("/comment/save/{clientId}/{userId}")
    public ResponseEntity<?> saveComment(@RequestBody Comment comment,
                                         @PathVariable Long clientId,
                                         @PathVariable Long userId) {
        return commentService.save(comment, clientId, userId);
    }

    @GetMapping("/comment/replays/{parentCommentId}")
    public ResponseEntity<?>  findCommentReplays(@PathVariable Long parentCommentId){
        return commentService.findCommentReplays(parentCommentId);
    }

    @GetMapping("/comment/parents/{clientId}")
    public ResponseEntity<?>  findCommentParents(@PathVariable Long clientId){
        return commentService.findCommentParents(clientId);
    }


    @PostMapping("comment/thumb-up/{commentId}/{votedUserId}")
    public ResponseEntity<?> updatePositiveRating(@PathVariable Long commentId,
                                                  @PathVariable Long votedUserId) {
        return commentService.updatePositiveRating(commentId, votedUserId);
    }

    @PostMapping("comment/thumb-down/{commentId}/{votedUserId}")
    public ResponseEntity<?> updateNegativeRating(@PathVariable Long commentId,
                                                  @PathVariable Long votedUserId) {
        return commentService.updateNegativeRating(commentId, votedUserId);
    }

}

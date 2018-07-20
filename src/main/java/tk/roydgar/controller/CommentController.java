package tk.roydgar.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.model.entity.Comment;
import tk.roydgar.model.service.CommentService;

import java.util.List;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

    private CommentService commentService;

    @GetMapping("/comments/{clientId}")
    public @ResponseBody List<Comment> findByClientId(@PathVariable Long clientId) {
        return commentService.findByClientId(clientId);
    }

    @PostMapping("/addComment/{clientId}/{userId}")
    public @ResponseBody Comment saveComment(@RequestBody Comment comment,
                                             @PathVariable Long clientId,
                                             @PathVariable Long userId) {
        return commentService.save(comment, clientId, userId);
    }

}

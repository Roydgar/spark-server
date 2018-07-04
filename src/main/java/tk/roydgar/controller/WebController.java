package tk.roydgar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spark.template.velocity.VelocityTemplateEngine;
import tk.roydgar.model.entity.Comment;
import tk.roydgar.model.service.ClientService;
import tk.roydgar.model.service.CommentService;
import tk.roydgar.model.service.NewsService;
import tk.roydgar.util.JsonTransformer;

import static spark.Spark.get;
import static spark.Spark.post;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WebController {

    private IndexPageController indexPageController;
    private ClientService clientService;
    private NewsService newsService;
    private CommentService commentService;

    public void setupRoutes() {

        get("/", indexPageController.handle, new VelocityTemplateEngine());
        get("/client/:name", (request, response) ->
                        clientService.findByName(request.params(":name"))
                , new JsonTransformer());

        get("/news/:clientId", (request, response) ->
                        newsService.findByClientId(request.params(":clientId"))
                , new JsonTransformer());

        get("/comments/:clientId", (request, response) ->
                        commentService.findByClientId(request.params(":clientId"))
                , new JsonTransformer());

        post("/addComment/:clientId", (request, response) ->
                        commentService.save(
                                new ObjectMapper().readValue(request.body(), Comment.class)
                                , request.params(":clientId"))
                , new JsonTransformer());

    }

}

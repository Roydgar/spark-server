package tk.roydgar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spark.template.velocity.VelocityTemplateEngine;
import tk.roydgar.model.entity.Comment;
import tk.roydgar.model.service.AppointmentService;
import tk.roydgar.model.service.ClientService;
import tk.roydgar.model.service.CommentService;
import tk.roydgar.model.service.NewsService;
import tk.roydgar.util.JsonTransformer;
import tk.roydgar.util.ModelAndViewUtil;
import tk.roydgar.util.constants.TemplatePaths;

import static spark.Spark.get;
import static spark.Spark.post;
import static tk.roydgar.util.ModelAndViewUtil.createModelAndView;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WebController {

    private IndexPageController indexPageController;
    private ClientRegistrationController clientRegistrationController;
    private ClientService clientService;
    private NewsService newsService;
    private CommentService commentService;
    private AppointmentService appointmentService;
    private VelocityTemplateEngine velocityTemplateEngine;
    private JsonTransformer jsonTransformer;

    public void setupRoutes() {

        get("/", indexPageController.handle, velocityTemplateEngine);

        get("/registration", (request, response) ->
                        createModelAndView(TemplatePaths.CLIENT_REGISTRATION_FROM),
                        velocityTemplateEngine);

        post("/registration", clientRegistrationController.handle, velocityTemplateEngine);

        get("/client/:name", (request, response) ->
                        clientService.findByName(request.params(":name"))
                , jsonTransformer);

        get("/news/:clientId", (request, response) ->
                        newsService.findByClientId(request.params(":clientId")), jsonTransformer);

        get("/comments/:clientId", (request, response) ->
                        commentService.findByClientId(request.params(":clientId"))
                , jsonTransformer);

        post("/addComment/:clientId", (request, response) ->
                        commentService.save(
                                new ObjectMapper().readValue(request.body(), Comment.class)
                                , request.params(":clientId"))
                , jsonTransformer);

        get("/appointments", (request, response) ->
            appointmentService.findByCurrentDate(), jsonTransformer);
    }

}

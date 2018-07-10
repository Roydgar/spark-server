package tk.roydgar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spark.template.velocity.VelocityTemplateEngine;
import tk.roydgar.model.entity.Appointment;
import tk.roydgar.model.entity.Comment;
import tk.roydgar.model.service.*;
import tk.roydgar.util.JsonTransformer;
import tk.roydgar.util.SmtpMailSender;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.route.HttpMethod.post;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WebController {

    private IndexPageController indexPageController;
    private ClientService clientService;
    private NewsService newsService;
    private CommentService commentService;
    private AppointmentService appointmentService;
    private ProcedureService procedureService;

    private VelocityTemplateEngine velocityTemplateEngine;
    private JsonTransformer jsonTransformer;


    public void setupRoutes() {

        get("/", indexPageController.handle, velocityTemplateEngine);


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

        get("/appointments/:clientId", (request, response) ->
            appointmentService.findByClientId(request.params(":clientId")), jsonTransformer);

        post("/addAppointment/:clientId", (request, response) ->
            appointmentService.save(
                    new ObjectMapper().readValue(request.body(), Appointment.class)
                    , request.params(":clientId"))
                    , jsonTransformer);


        get("services/:clientId", (request, response) ->
            procedureService.findByClientId(request.params(":clientId")), jsonTransformer);

        }

}

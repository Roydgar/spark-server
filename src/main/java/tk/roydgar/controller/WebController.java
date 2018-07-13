package tk.roydgar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tk.roydgar.model.entity.Appointment;
import tk.roydgar.model.entity.Comment;
import tk.roydgar.model.service.*;
import tk.roydgar.util.JsonTransformer;

import static spark.Spark.get;
import static spark.Spark.post;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WebController {

    private ClientService clientService;
    private NewsService newsService;
    private CommentService commentService;
    private AppointmentService appointmentService;
    private ProcedureService procedureService;

    private JsonTransformer jsonTransformer;
    private ObjectMapper objectMapperDeserializer;
    
    public void setupRoutes() {

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

        post("/addAppointment/:clientId/:serviceId", (request, response) ->
            appointmentService.save(
                   objectMapperDeserializer.readValue(request.body(), Appointment.class)
                    , request.params(":clientId")
                    , request.params(":serviceId"))
                    , jsonTransformer);

        get("services/:clientId", (request, response) ->
            procedureService.findByClientId(request.params(":clientId")), jsonTransformer);

        }

}

package tk.roydgar.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spark.template.velocity.VelocityTemplateEngine;
import tk.roydgar.model.entity.Client;
import tk.roydgar.model.service.ClientService;
import tk.roydgar.util.JsonTransformer;

import java.time.LocalDateTime;

import static spark.Spark.get;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WebController {

    private IndexPageController indexPageController;
    private ClientService clientService;

    public void setupRoutes() {

        get("/", indexPageController.handle, new VelocityTemplateEngine());
        get("/findAll", (request, response) -> clientService.findAll(), new JsonTransformer());
        get("/add", (request, response) ->  {
            Client client = Client.builder()
                    .name("sto").password("0000")
                    .login("client").phone("88005555535")
                    .registrationDate(LocalDateTime.now())
                    .email("royd@mail.ru")
                    .address("SDFSDF").build();
            return clientService.save(client);
        });

    }

}

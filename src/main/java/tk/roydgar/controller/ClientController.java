package tk.roydgar.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.model.entity.client.Client;
import tk.roydgar.model.service.ClientService;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController {

    private ClientService clientService;

    @GetMapping("/client/name/{clientName}")
    public @ResponseBody Client findByName(@PathVariable String clientName) {
        return clientService.findByName(clientName);
    }

    @GetMapping("/client/id/{clientId}")
    public @ResponseBody Client findById(@PathVariable Long clientId) {
        return clientService.findById(clientId);
    }
}

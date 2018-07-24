package tk.roydgar.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.model.entity.client.Client;
import tk.roydgar.model.service.ClientService;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController {

    private ClientService clientService;

    @GetMapping("/client/name/{clientName}")
    public ResponseEntity<?> findByName(@PathVariable String clientName) {
        return clientService.findByName(clientName);
    }

    @GetMapping("/client/id/{clientId}")
    public ResponseEntity<?> findById(@PathVariable Long clientId) {
        return clientService.findById(clientId);
    }

}

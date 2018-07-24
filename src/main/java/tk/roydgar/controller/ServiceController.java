package tk.roydgar.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tk.roydgar.model.service.ServiceService;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceController {

    private ServiceService service;

    @GetMapping("services/{clientId}")
    public ResponseEntity<?> findByClientId(@PathVariable Long clientId) {
        return service.findByClientId(clientId);
    }

}

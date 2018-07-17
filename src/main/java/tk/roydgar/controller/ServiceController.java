package tk.roydgar.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.roydgar.model.entity.Service;
import tk.roydgar.model.service.ServiceService;

import java.util.List;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceController {

    private ServiceService service;

    @GetMapping("services/{clientId}")
    public @ResponseBody
    List<Service> findByClientId(@PathVariable Long clientId) {
        return service.findByClientId(clientId);
    }

}

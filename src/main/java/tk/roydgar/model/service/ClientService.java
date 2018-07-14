package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.roydgar.model.entity.Client;
import tk.roydgar.model.repository.ClientRepository;
import tk.roydgar.util.HashUtil;
import tk.roydgar.util.Utils;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private ClientRepository clientRepository;

    public Client findByName(String name) {
        return clientRepository.findByName(name);
    }

}

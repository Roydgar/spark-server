package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.client.Client;
import tk.roydgar.model.repository.ClientRepository;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private ClientRepository clientRepository;
    private Logger logger;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Client findByName(String name) {
        logger.info("findByName() call; name = " + name);
        return clientRepository.findByName(name);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Client findById(Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        return client.orElse(null);
    }

}

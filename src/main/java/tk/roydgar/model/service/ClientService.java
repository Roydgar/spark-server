package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.Client;
import tk.roydgar.model.repository.ClientRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private ClientRepository clientRepository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Client findByName(String name) {
        return clientRepository.findByName(name);
    }

}

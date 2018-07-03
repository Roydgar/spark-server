package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.roydgar.model.entity.Client;
import tk.roydgar.model.repository.ClientRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private ClientRepository clientRepository;

    public Client save(Client client) {
        client.setRegistrationDate(ZonedDateTime.now().withZoneSameInstant(ZoneId.of("GMT"))
                .toLocalDateTime());
        return clientRepository.save(client);
    }

    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

}

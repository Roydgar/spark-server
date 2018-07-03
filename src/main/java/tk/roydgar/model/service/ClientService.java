package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.roydgar.model.entity.Client;
import tk.roydgar.model.entity.WorkTime;
import tk.roydgar.model.repository.ClientRepository;
import tk.roydgar.model.repository.WorkTimeRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private ClientRepository clientRepository;
    private WorkTimeRepository workTimeRepository;

    public Client save(Client client) {
        client.setRegistrationDate(ZonedDateTime.now().withZoneSameInstant(ZoneId.of("GMT"))
                .toLocalDateTime());
        return clientRepository.save(client);
    }

    public Iterable<Client> findAll() {
        Iterable<Client> clientList = clientRepository.findAll();
        clientList.forEach(client -> client.setWorkDays(workTimeRepository.findByClientId(client.getId())));
        return clientList;
    }

}

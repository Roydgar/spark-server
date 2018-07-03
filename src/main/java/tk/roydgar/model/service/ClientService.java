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

    public Client findByName(String name) {
        Client client = clientRepository.findByName(name);
        if (client == null) {
            return null;
        }

        client.setWorkDays(workTimeRepository.findByClientId(client.getId()));
        return client;
    }

}

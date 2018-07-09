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

    public Client login(String login, String password) {
        Client client = clientRepository.findByLogin(login);
        if (client == null) {
            return null;
        }

        if (!HashUtil.check(password, client.getPassword())) {
            return null;
        }

        return client;
    }

    public Client save(Client client) {
        if (client == null) {
            return null;
        }

        if (clientWithGivenLoginExists(client.getLogin())) {
            return null;
        }

        client.setPassword(HashUtil.hash(client.getPassword()));
        client.setRegistrationDate(Utils.getLocalDateTimeInUTC());

        return clientRepository.save(client);
    }

    private boolean clientWithGivenLoginExists(String login) {
        return clientRepository.findByLogin(login) != null;
    }

}

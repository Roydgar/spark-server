package tk.roydgar.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tk.roydgar.model.entity.client.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findByName(String name);
    Client findByLogin(String login);
}

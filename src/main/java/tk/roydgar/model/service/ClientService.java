package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.client.Client;
import tk.roydgar.model.repository.ClientRepository;

import java.util.Optional;

import static tk.roydgar.util.ResponseEntityUtil.responseEntityFromOptional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private ClientRepository clientRepository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity<?> findByName(String name) {
        return responseEntityFromOptional(clientRepository.findByName(name));
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity findById(Long clientId) {
        return responseEntityFromOptional(clientRepository.findById(clientId));
    }

}

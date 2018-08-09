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

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static tk.roydgar.util.HttpHeadersUtil.httpHeaders;
import static tk.roydgar.util.ResponseEntityUtil.responseEntityFromOptional;
import static tk.roydgar.util.constants.HeaderMessages.ENTITIES_NOT_FOUND;
import static tk.roydgar.util.constants.HeaderMessages.HEADER_KEY;
import static tk.roydgar.util.constants.HeaderMessages.USER_NOT_SERVED_BY_THIS_CLIENT;

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

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity findServedUsersByClientId(Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);

        if (!client.isPresent()) {
            return new ResponseEntity<>(
                    httpHeaders(HEADER_KEY, ENTITIES_NOT_FOUND), NOT_FOUND);
        }

        return new ResponseEntity<>(client.get().getUsers(), OK);
    }

}

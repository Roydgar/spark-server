package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.Service;
import tk.roydgar.model.repository.ServiceRepository;

import java.util.List;

import static tk.roydgar.util.ResponseEntityUtil.responseEntityFromList;

@org.springframework.stereotype.Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceService {

    private ServiceRepository serviceRepository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity<?> findByClientId(Long clientId) {
        return responseEntityFromList(serviceRepository.findAllByClientId(clientId));
    }

}

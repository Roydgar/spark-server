package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.Service;
import tk.roydgar.model.repository.ServiceRepository;

import java.util.List;

@org.springframework.stereotype.Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceService {

    private ServiceRepository serviceRepository;
    private Logger logger;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Service> findByClientId(Long clientId) {
        logger.info("findByClientId() call; clientId = " + clientId);
        return serviceRepository.findAllByClientId(clientId);
    }

}

package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.Procedure;
import tk.roydgar.model.repository.ProcedureRepository;
import tk.roydgar.util.Utils;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProcedureService {

    private ProcedureRepository procedureRepository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Procedure> findByClientId(Long clientId) {
        return procedureRepository.findAllByClientId(clientId);
    }

}

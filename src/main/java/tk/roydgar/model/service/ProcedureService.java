package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.roydgar.model.entity.Procedure;
import tk.roydgar.model.repository.ProcedureRepository;
import tk.roydgar.util.Utils;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))

public class ProcedureService {

    private ProcedureRepository procedureRepository;

    public List<Procedure> findByClientId(String clientId) {
        Long id = Utils.parseId(clientId);
        if (id == null) {
            return null;
        }

        return procedureRepository.findAllByClientId(id);
    }

}

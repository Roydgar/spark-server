package tk.roydgar.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tk.roydgar.model.entity.Comment;
import tk.roydgar.model.entity.Procedure;

import java.util.List;

@Repository
public interface ProcedureRepository extends CrudRepository<Procedure, Long> {
    List<Procedure> findAllByClientId(Long client_client_id);
}

package tk.roydgar.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tk.roydgar.model.entity.WorkTime;

import java.util.List;

@Repository
public interface WorkTimeRepository extends CrudRepository<WorkTime, Long> {

    List<WorkTime> findByClientId(Long client_client_id);
}

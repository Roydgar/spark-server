package tk.roydgar.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tk.roydgar.model.entity.client.WorkTime;

import java.util.List;

@Repository
public interface WorkTimeRepository extends CrudRepository<WorkTime, Long> {

    List<WorkTime> findAllByClientId(Long client_client_id);
}

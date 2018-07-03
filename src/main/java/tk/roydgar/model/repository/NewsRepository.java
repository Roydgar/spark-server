package tk.roydgar.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tk.roydgar.model.entity.News;


@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
    News findByClientId(Long client_client_id);
}

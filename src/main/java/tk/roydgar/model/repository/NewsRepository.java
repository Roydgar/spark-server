package tk.roydgar.model.repository;

import org.springframework.data.repository.CrudRepository;
import tk.roydgar.model.entity.News;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long> {
    List<News> findByClientId(Long client_client_id);
}

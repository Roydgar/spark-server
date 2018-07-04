package tk.roydgar.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tk.roydgar.model.entity.News;

import java.util.List;


@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
    List<News> findAllByClientId(Long client_client_id);
}

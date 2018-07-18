package tk.roydgar.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tk.roydgar.model.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}

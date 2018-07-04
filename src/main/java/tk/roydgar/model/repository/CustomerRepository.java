package tk.roydgar.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tk.roydgar.model.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}

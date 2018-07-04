package tk.roydgar.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tk.roydgar.model.entity.Appointment;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    @Query(value = "SELECT * FROM appointment WHERE DATE(time) = ?1", nativeQuery = true)
    List<Appointment> findAllByDate(LocalDate date);

}
package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.roydgar.model.entity.Appointment;
import tk.roydgar.model.repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppointmentService {

    private AppointmentRepository appointmentRepository;

    public Iterable<Appointment> findByCurrentDate() {
        return appointmentRepository.findAllByDate(LocalDate.now());
    }

}

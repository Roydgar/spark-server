package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.roydgar.model.entity.Appointment;
import tk.roydgar.model.entity.Client;
import tk.roydgar.model.repository.AppointmentRepository;
import tk.roydgar.model.repository.ClientRepository;
import tk.roydgar.util.Utils;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private ClientRepository clientRepository;

    public Iterable<Appointment> findByCurrentDate() {
        return appointmentRepository.findAllByDate(Utils.getLocalDateInUTC());
    }

    public Iterable<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Iterable<Appointment> findByClientId(String clientId) {
        Long id = Utils.parseId(clientId);
        if (id == null) {
            return null;
        }

        return appointmentRepository.findAllByClientId(id);
    }

    public Appointment save(Appointment appointment, String clientId) {
        Long id = Utils.parseId(clientId);
        if (appointment == null || id == null) {
            return null;
        }

        return clientRepository.findById(id).map(client -> {
            appointment.setClient(client);
            return appointmentRepository.save(appointment);
        }).orElse(null);
    }

}

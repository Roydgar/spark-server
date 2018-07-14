package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.roydgar.model.entity.Appointment;
import tk.roydgar.model.entity.Client;
import tk.roydgar.model.entity.Procedure;
import tk.roydgar.model.repository.AppointmentRepository;
import tk.roydgar.model.repository.ClientRepository;
import tk.roydgar.model.repository.CustomerRepository;
import tk.roydgar.model.repository.ProcedureRepository;
import tk.roydgar.util.Utils;

import javax.imageio.spi.ServiceRegistry;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private ClientRepository clientRepository;
    private ProcedureRepository procedureRepository;

    public List<Appointment> findByClientId(String clientId) {
        Long id = Utils.parseId(clientId);
        if (id == null) {
            return null;
        }

        return appointmentRepository.findAllByClientId(id);
    }

    public List<Appointment> findByDateAndClientId(String clientId, String date) {
        Long id = Utils.parseId(clientId);
        if (id == null) {
            return null;
        }

        try {
            LocalDate parsedDate = LocalDate.parse(date);
            LocalDateTime from = LocalDateTime.of(parsedDate, LocalTime.of(0, 0, 0));
            LocalDateTime to = LocalDateTime.of(parsedDate, LocalTime.of(23, 59, 59));

            return appointmentRepository.findAllByClientIdAndTimeBetween(id, from, to);
        } catch (Exception e) {
            return null;
        }
    }

    public Appointment save(Appointment appointment, String clientId, String serviceId) {
        Long idClient = Utils.parseId(clientId);
        Long idService = Utils.parseId(serviceId);
        if (appointment == null || idClient == null || idService == null) {
            return null;
        }

        Optional<Client> client = clientRepository.findById(idClient);
        Optional<Procedure> procedure = procedureRepository.findById(idService);

        if (!client.isPresent() || !procedure.isPresent()) {
            return null;
        }

        appointment.setClient(client.get());
        appointment.setProcedure(procedure.get());

        return appointmentRepository.save(appointment);
    }

}

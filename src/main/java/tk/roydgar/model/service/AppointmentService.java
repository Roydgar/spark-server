package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.Appointment;
import tk.roydgar.model.entity.Client;
import tk.roydgar.model.entity.Procedure;
import tk.roydgar.model.repository.AppointmentRepository;
import tk.roydgar.model.repository.ClientRepository;
import tk.roydgar.model.repository.ProcedureRepository;

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

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Appointment> findByClientId(Long clientId) {
        return appointmentRepository.findAllByClientId(clientId);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Appointment> findByDateAndClientId(Long clientId, String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            LocalDateTime from = LocalDateTime.of(parsedDate, LocalTime.of(0, 0, 0));
            LocalDateTime to = LocalDateTime.of(parsedDate, LocalTime.of(23, 59, 59));

            return appointmentRepository.findAllByClientIdAndTimeBetween(clientId, from, to);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Appointment save(Appointment appointment, Long clientId, Long serviceId) {
        if (appointment == null) {
            return null;
        }

        Optional<Client> client = clientRepository.findById(clientId);
        Optional<Procedure> procedure = procedureRepository.findById(serviceId);

        if (!client.isPresent() || !procedure.isPresent()) {
            return null;
        }

        appointment.setClient(client.get());
        appointment.setProcedure(procedure.get());

        return appointmentRepository.save(appointment);
    }

}

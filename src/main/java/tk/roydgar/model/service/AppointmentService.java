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
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private ClientRepository clientRepository;
    private ProcedureRepository procedureRepository;

    public Iterable<Appointment> findByClientId(String clientId) {
        Long id = Utils.parseId(clientId);
        if (id == null) {
            return null;
        }

        return appointmentRepository.findAllByClientId(id);
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

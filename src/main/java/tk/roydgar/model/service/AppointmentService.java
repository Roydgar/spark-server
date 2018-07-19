package tk.roydgar.model.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.model.entity.Appointment;
import tk.roydgar.model.entity.client.Client;
import tk.roydgar.model.entity.Service;
import tk.roydgar.model.repository.AppointmentRepository;
import tk.roydgar.model.repository.ClientRepository;
import tk.roydgar.model.repository.ServiceRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private ClientRepository clientRepository;
    private ServiceRepository serviceRepository;
    private Logger logger;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Appointment> findByClientId(Long clientId) {
        logger.info("findByClientId() call; clientId = " + clientId);
        return appointmentRepository.findAllByClientId(clientId);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Appointment> findByDateAndClientId(Long clientId, String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            LocalDateTime from = LocalDateTime.of(parsedDate, LocalTime.of(0, 0, 0));
            LocalDateTime to = LocalDateTime.of(parsedDate, LocalTime.of(23, 59, 59));

            logger.info("findByDateAndClientId() call; SUCCESS; clientId = " + clientId +
                "; date = " + date);
            return appointmentRepository.findAllByClientIdAndTimeBetween(clientId, from, to);
        } catch (Exception e) {
            logger.info("findByDateAndClientId() call; FAILURE; date parsing error; " +
                    "clientId = " + clientId + "; date = " + date);
            return null;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Appointment save(Appointment appointment, Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);

        if (!client.isPresent()) {
            logger.info("save() call; FAILURE; client not found by client id; client id = " + clientId);
            return null;
        }

        appointment.setClient(client.get());

        List<Service> appointmentServices = new ArrayList<>();

        for (Long serviceId : appointment.getServicesId()) {
            Optional<Service> service = serviceRepository.findById(serviceId);
            service.ifPresent(appointmentServices::add);
        }

        appointment.setServices(appointmentServices);

        logger.info("save() call; SUCCESS; client id = " + clientId);
        return appointmentRepository.save(appointment);
    }

}

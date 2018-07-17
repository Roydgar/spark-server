package tk.roydgar.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.model.entity.Appointment;
import tk.roydgar.model.service.AppointmentService;

import java.util.List;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppointmentController {

    private AppointmentService appointmentService;

    @GetMapping("/appointments/{clientId}")
    public @ResponseBody
    List<Appointment> findByClientId(@PathVariable Long clientId) {
        return appointmentService.findByClientId(clientId);
    }

    @GetMapping("/appointments/{clientId}/{date}")
    public @ResponseBody
    List<Appointment> findByDateAndClientId(@PathVariable Long clientId,
                                            @PathVariable String date) {
        return appointmentService.findByDateAndClientId(clientId, date);
    }

    @PostMapping("addAppointment/{clientId}/{serviceId}")
    public @ResponseBody Appointment saveAppointment(@PathVariable Long clientId,
                                                     @PathVariable Long serviceId,
                                                     @RequestBody Appointment appointment) {
        return appointmentService.save(appointment, clientId, serviceId);
    }

}

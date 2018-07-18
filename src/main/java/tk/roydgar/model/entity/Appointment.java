package tk.roydgar.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tk.roydgar.model.entity.client.Client;
import tk.roydgar.model.entity.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @NotNull
    @Column(name = "time", nullable = false, updatable = false)
    private LocalDateTime time;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "service_id")
    @JsonProperty("service")
    private Service service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;
}

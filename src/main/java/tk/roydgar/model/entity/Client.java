package tk.roydgar.model.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
@Builder
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @NotNull
    @Column(nullable = false)
    @JsonIgnore
    private String login;

    @NotNull
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @NotNull
    @Column(nullable = false, length = 80)
    private String name;

    @NotNull
    @Column(nullable = false, length = 30)
    private String phone;

    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registrationDate;

    @NotNull
    @Column(nullable = false)
    private String address;

    @Transient
    private List<WorkTime> workDays;

}

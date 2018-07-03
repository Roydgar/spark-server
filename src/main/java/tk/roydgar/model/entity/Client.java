package tk.roydgar.model.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    private Long id;

    @NotNull
    @JsonIgnore
    private String login;

    @JsonIgnore
    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String phone;

    @NotNull
    private String email;

    @NotNull
    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registrationDate;

    @NotNull
    private String address;

}

package tk.roydgar.model.entity.client;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@EqualsAndHashCode
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
    @Column(nullable = false, unique = true)
    @JsonIgnore
    private String login;

    @NotNull
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @Column(nullable = false, length = 80)
    private String name;

    @NotNull
    @Column(nullable = false, length = 30)
    private String phone;

    @Column
    private String logoURL;

    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registrationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private List<WorkTime> workDays;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private List<Advantage> advantages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addresses;

}

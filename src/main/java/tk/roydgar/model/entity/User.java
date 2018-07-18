package tk.roydgar.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tk.roydgar.model.entity.embeddable.Car;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @NotNull
    @Column(nullable = false, length = 40)
    private String name;

    @NotNull
    @Column(nullable = false, length = 60)
    private String surname;

    @NotNull
    @Column(nullable = false, length = 20)
    private String phone;

    @NotNull
    @Column(nullable = false, length = 60)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Embedded
    private Car car;

    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private Appointment appointment;

}

package tk.roydgar.model.entity;

import lombok.*;
import tk.roydgar.model.entity.embeddable.Car;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
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
    @Column(nullable = false, length = 20)
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.GUEST;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Embedded
    private Car car;

    public enum Status {
        GUEST, CONFIRMED
    }

}

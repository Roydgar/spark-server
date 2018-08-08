package tk.roydgar.model.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString(exclude = "password")
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Car> cars;

    public enum Status {
        GUEST, CONFIRMED
    }

}

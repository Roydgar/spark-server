package tk.roydgar.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customer")
public class Customer {

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

    @Column(length = 60)
    private String email;

    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private Appointment appointment;
}

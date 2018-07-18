package tk.roydgar.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tk.roydgar.model.entity.client.Client;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(name = "duration_in_minutes", nullable = false)
    private Integer durationInMinutes;

    @NotNull
    @Column(nullable = false)
    private Long price;

    @NotNull
    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

}

package tk.roydgar.model.entity.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @NotNull
    @Column(nullable = false, length = 60)
    private String city;

    @NotNull
    @Column(nullable = false, length = 60)
    private String street;

    @NotNull
    @Column(nullable = false, length = 30)
    private String house;

    @NotNull
    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;

}

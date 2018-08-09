package tk.roydgar.model.entity.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "mobile_number")
public class MobileNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mobile_number_id")
    private Long id;

    @Column
    private String operator;

    @NotNull
    @Column(nullable= false)
    private String number;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "client_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;

}

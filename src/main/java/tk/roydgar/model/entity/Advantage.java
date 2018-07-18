package tk.roydgar.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(name = "advantage")
public class Advantage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advantage_id")
    private Long id;

    @NotNull
    @Column(nullable = false, length = 60)
    String title;

    @NotNull
    @Column(nullable = false)
    String text;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;
}

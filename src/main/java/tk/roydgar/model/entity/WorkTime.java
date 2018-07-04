package tk.roydgar.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter @Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "work_time")
public class WorkTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_time_id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "from_day",  nullable = false, length = 12)
    private DayOfWeek fromDay;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "to_day",  nullable = false, length = 12)
    private DayOfWeek toDay;

    @NotNull
    @Column(name = "from_time",  nullable = false)
    private LocalTime fromTime;

    @NotNull
    @Column(name = "to_time",  nullable = false)
    private LocalTime toTime;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

}

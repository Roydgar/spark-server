package tk.roydgar.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "work_time")
public class WorkTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "work_time_id")
    private Integer id;

    @NotNull
    @Size(max = 2)
    @Column(name = "from_day",  nullable = false)
    private String fromDay;

    @NotNull
    @Size(max = 2)
    @Column(name = "from_hour",  nullable = false)
    private String fromHour;

    @NotNull
    @Size(max = 2)
    @Column(name = "from_minute",  nullable = false)
    private String fromMinute;

    @NotNull
    @Size(max = 2)
    @Column(name = "to_day",  nullable = false)
    private String toDay;

    @NotNull
    @Size(max = 2)
    @Column(name = "to_hour",  nullable = false)
    private String toHour;

    @NotNull
    @Size(max = 2)
    @Column(name = "to_minute",  nullable = false)
    private String toMinute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Client client;

}

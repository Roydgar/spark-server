package tk.roydgar.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "parent_id")
    private Long parentId = 0L;

    @NotNull
    @Column(nullable = false)
    @Lob
    private String text;

    @NotNull
    @Column(nullable = false)
    @Max(5)
    @Min(0)
    private Integer mark;

    @NotNull
    @Column(name = "positive_rating", nullable = false)
    private Integer positiveRating = 0;

    @NotNull
    @Column(name = "negative_rating", nullable = false)
    private Integer negativeRating = 0;

    @NotNull
    @Column(name = "time", nullable = false, updatable = false)
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

}

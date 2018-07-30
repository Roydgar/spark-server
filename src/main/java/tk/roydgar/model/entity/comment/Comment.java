package tk.roydgar.model.entity.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tk.roydgar.model.entity.client.Client;
import tk.roydgar.model.entity.user.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

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
    private Integer mark = 0;

    @NotNull
    @Column(name = "positive_rating", nullable = false)
    private Integer positiveRating = 0;

    @NotNull
    @Column(name = "negative_rating", nullable = false)
    private Integer negativeRating = 0;

    @NotNull
    @Column(name = "replay_count", nullable = false)
    private Integer replayCount = 0;

    @NotNull
    @Column(name = "time", nullable = false, updatable = false)
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Vote> votes;

}

package tk.roydgar.model.entity.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tk.roydgar.model.entity.user.User;

import javax.persistence.*;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private TYPE type;

    public enum TYPE {
        THUMB_UP, THUMB_DOWN
    }

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Comment comment;

}

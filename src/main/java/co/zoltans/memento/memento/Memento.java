package co.zoltans.memento.memento;

import co.zoltans.memento.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@ToString
@Setter
@NoArgsConstructor
@Table(name = "memento")
@Entity(name = "Memento")
public class Memento {

    @Id
    @SequenceGenerator(
            name = "memento_sequence",
            sequenceName = "memento_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "memento_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(name = "reminder")
    private String reminder;

    @Column(name = "priority")
    private Priority priority;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "user_memento_fk"
            )
    )
    private User user;

    public Memento(final User user, final String reminder, final Priority priority) {
        this.user = user;
        this.reminder = reminder;
        this.priority = priority;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("reminder")
    public String getReminder() {
        return reminder;
    }

    @JsonProperty("priority")
    public Priority getPriority() {
        return priority;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public User getUser() {
        return user;
    }
}

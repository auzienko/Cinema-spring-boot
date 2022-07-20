package edu.school21.cinema.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(schema = "cinema", name="user_auth_history")
public class UserAuthHistory extends BaseEntity{
    @Column(name = "date_time")
    private LocalDateTime date = LocalDateTime.now();;

    @Column(name = "ip")
    private String ip;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private CinemaUser administrator;

    public String toDateTimeString(String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
}

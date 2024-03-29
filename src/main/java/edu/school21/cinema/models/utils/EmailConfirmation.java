package edu.school21.cinema.models.utils;

import edu.school21.cinema.models.BaseEntity;
import edu.school21.cinema.models.CinemaUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "cinema", name = "email_confirmations")
public class EmailConfirmation extends BaseEntity {
    @Column(name = "token")
    String token;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    CinemaUser cinemaUser;
}

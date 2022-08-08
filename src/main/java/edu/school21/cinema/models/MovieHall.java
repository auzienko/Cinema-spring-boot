package edu.school21.cinema.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(schema = "cinema", name="movie_halls")
public class MovieHall extends BaseEntity{
    @Column(name = "serial_number")
    private Integer serialNumber;

    @Column(name = "seats")
    private Integer seats;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    private CinemaUser administrator;
}

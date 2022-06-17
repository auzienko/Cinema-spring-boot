package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByAdministrator_Id(Long id);
}

package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findByAdministrator_Id(Long id);
}

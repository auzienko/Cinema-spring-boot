package edu.school21.cinema.repositories;

import edu.school21.cinema.models.MovieHall;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovieHallRepository extends CrudRepository<MovieHall, Long> {
    Optional<MovieHall> findBySerialNumber(Integer serialNumber);
    List<MovieHall> findByAdministrator_Id(Long id);
}

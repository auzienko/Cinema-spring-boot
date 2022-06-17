package edu.school21.cinema.repositories;

import edu.school21.cinema.models.MovieHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MovieHallRepository extends JpaRepository<MovieHall, Long> {
    Optional<MovieHall> findBySerialNumber(Integer serialNumber);
    List<MovieHall> findByAdministrator_Id(Long id);
}

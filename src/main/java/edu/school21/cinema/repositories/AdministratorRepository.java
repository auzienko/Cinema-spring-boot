package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Administrator;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdministratorRepository extends CrudRepository<Administrator, Long> {
    Optional<Administrator> findByEmailIgnoreCase(String email);
}

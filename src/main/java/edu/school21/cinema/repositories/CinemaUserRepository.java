package edu.school21.cinema.repositories;

import edu.school21.cinema.models.CinemaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaUserRepository extends JpaRepository<CinemaUser, Long> {
    CinemaUser findByUsername(String username);
    CinemaUser findByEmail(String email);

    Optional<CinemaUser> findByEmailIgnoreCase(String email);
}

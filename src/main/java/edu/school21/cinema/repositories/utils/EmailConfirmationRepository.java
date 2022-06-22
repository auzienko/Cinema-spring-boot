package edu.school21.cinema.repositories.utils;

import edu.school21.cinema.models.utils.EmailConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailConfirmationRepository extends JpaRepository<EmailConfirmation, Long> {
    Optional<EmailConfirmation> findByCinemaUser_Id(Long id);
}

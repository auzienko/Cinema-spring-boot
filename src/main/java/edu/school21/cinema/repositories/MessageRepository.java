package edu.school21.cinema.repositories;

import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> getTopByMovie_Id(Long movieId);
}

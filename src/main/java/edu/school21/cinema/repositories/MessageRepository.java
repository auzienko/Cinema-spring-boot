package edu.school21.cinema.repositories;

import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> getFirst20ByMovie_Id(Long movieId);
}

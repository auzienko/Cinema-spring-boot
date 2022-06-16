package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session, Long> {
    List<Session> findByMovie_TitleIgnoreCase(String title);
}

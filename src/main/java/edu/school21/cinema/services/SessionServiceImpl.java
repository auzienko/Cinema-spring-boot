package edu.school21.cinema.services;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }


    @Override
    public Optional<Session> get(Long id) {
        return sessionRepository.findById(id);
    }

    @Override
    public List<Session> getAll() {
        return (List<Session>) sessionRepository.findAll();
    }

    @Override
    public void add(Session entity) {
        sessionRepository.save(entity);
    }

    @Override
    public List<Session> searchByTitle(String title) {
        return sessionRepository.findByMovie_TitleContainingIgnoreCase(title);
    }
}

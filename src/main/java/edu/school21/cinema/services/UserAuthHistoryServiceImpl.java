package edu.school21.cinema.services;

import edu.school21.cinema.models.UserAuthHistory;
import edu.school21.cinema.repositories.UserAuthHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserAuthHistoryServiceImpl implements UserAuthHistoryService {
    private final UserAuthHistoryRepository userAuthHistoryRepository;


    @Override
    public void save(UserAuthHistory entity) {
        userAuthHistoryRepository.save(entity);
    }

    @Override
    public List<UserAuthHistory> getUserAuthHistory(Long id) {
        return userAuthHistoryRepository.findByAdministrator_Id(id);
    }

    @Override
    public Optional<UserAuthHistory> get(Long id) {
        return userAuthHistoryRepository.findById(id);
    }

    @Override
    public List<UserAuthHistory> getAll() {
        return userAuthHistoryRepository.findAll();
    }

    @Override
    public void add(UserAuthHistory entity) {
        userAuthHistoryRepository.save(entity);
    }
}

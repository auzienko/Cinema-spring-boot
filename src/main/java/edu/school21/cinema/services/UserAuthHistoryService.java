package edu.school21.cinema.services;

import edu.school21.cinema.models.UserAuthHistory;

import java.util.List;

public interface UserAuthHistoryService extends BaseService<UserAuthHistory> {
    void save(UserAuthHistory entity);
    List<UserAuthHistory> getUserAuthHistory(Long id);
}

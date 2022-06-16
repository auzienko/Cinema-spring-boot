package edu.school21.cinema.services;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    Optional<T> get(Long id);
    List<T> getAll();
    void add(T entity);
}

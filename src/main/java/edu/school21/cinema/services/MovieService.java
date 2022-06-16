package edu.school21.cinema.services;

import edu.school21.cinema.models.Movie;

import java.util.List;

public interface MovieService extends BaseService<Movie>{
    List<Movie> getAllByAdministratorId(Long id);
}

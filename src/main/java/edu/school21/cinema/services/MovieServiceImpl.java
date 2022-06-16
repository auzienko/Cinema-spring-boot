package edu.school21.cinema.services;

import edu.school21.cinema.models.Movie;
import edu.school21.cinema.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Optional<Movie> get(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> getAll() {
        return (List<Movie>) movieRepository.findAll();
    }

    @Override
    public void add(Movie entity) {
        movieRepository.save(entity);
    }

    @Override
    public List<Movie> getAllByAdministratorId(Long id) {
        return movieRepository.findByAdministrator_Id(id);
    }
}

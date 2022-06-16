package edu.school21.cinema.services;

import edu.school21.cinema.models.MovieHall;
import edu.school21.cinema.repositories.MovieHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieHallServiceImpl implements MovieHallService {
    private final MovieHallRepository movieHallRepository;

    @Autowired
    public MovieHallServiceImpl(MovieHallRepository movieHallRepository) {
        this.movieHallRepository = movieHallRepository;
    }

    @Override
    public void add(MovieHall entity) {
        movieHallRepository.save(entity);
    }

    @Override
    public List<MovieHall> getAll() {
        return (List<MovieHall>) movieHallRepository.findAll();
    }

    @Override
    public List<MovieHall> getAllByAdministratorId(Long id) {
        return movieHallRepository.findByAdministrator_Id(id);
    }

    @Override
    public Optional<MovieHall> get(Long id) {
        return movieHallRepository.findById(id);
    }

    @Override
    public Optional<MovieHall> getBySerialNumber(Integer serialNumber) {
        return movieHallRepository.findBySerialNumber(serialNumber);
    }
}

package edu.school21.cinema.services;

import edu.school21.cinema.models.Poster;
import edu.school21.cinema.repositories.PosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosterServiceImpl implements PosterService {

    private final PosterRepository posterRepository;

    @Autowired
    public PosterServiceImpl(PosterRepository posterRepository) {
        this.posterRepository = posterRepository;
    }

    @Override
    public Optional<Poster> get(Long id) {
        return posterRepository.findById(id);
    }

    @Override
    public List<Poster> getAll() {
        return (List<Poster>) posterRepository.findAll();
    }

    @Override
    public void add(Poster entity) {
        posterRepository.save(entity);
    }
}

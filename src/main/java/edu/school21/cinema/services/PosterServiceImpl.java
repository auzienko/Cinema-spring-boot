package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.ImageType;
import edu.school21.cinema.repositories.PosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PosterServiceImpl implements PosterService {

    private final PosterRepository posterRepository;

    @Autowired
    public PosterServiceImpl(PosterRepository posterRepository) {
        this.posterRepository = posterRepository;
    }

    @Override
    public Optional<Image> get(Long id) {
        return posterRepository.findById(id);
    }

    @Override
    public List<Image> getAll() {
        return (List<Image>) posterRepository.findAll();
    }

    @Override
    public void add(Image entity) {
        posterRepository.save(entity);
    }

    @Override
    public List<Image> getAllAvatars(Long id, ImageType type) {
        return posterRepository.findAllByAdministrator_IdAndType(id, type);
    }

    @Override
    public Optional<Image>  findByUUID(UUID fileNameUUID) {
        return posterRepository.findByFileNameUUID(fileNameUUID);
    }
}

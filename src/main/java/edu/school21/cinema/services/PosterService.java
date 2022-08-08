package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.ImageType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PosterService extends BaseService<Image> {
    List<Image> getAllAvatars(Long id, ImageType type);
    Optional<Image> findByUUID(UUID fileNameUUID);
}


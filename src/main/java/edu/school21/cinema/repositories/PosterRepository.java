package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PosterRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByAdministrator_IdAndType(Long id, ImageType type);
    Optional<Image> findByFileNameUUID(UUID fileNameUUID);
}

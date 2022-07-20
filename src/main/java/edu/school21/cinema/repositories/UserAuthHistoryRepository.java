package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.models.UserAuthHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthHistoryRepository extends JpaRepository<UserAuthHistory, Long> {
    List<UserAuthHistory> findByAdministrator_Id(Long id);
}

package edu.school21.cinema.services;

import edu.school21.cinema.models.CinemaUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface CinemaUserService extends UserDetailsService {

    Optional<CinemaUser> signUp(CinemaUser entity);

    Optional<CinemaUser> signIn(String email, String password);
}

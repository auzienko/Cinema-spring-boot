package edu.school21.cinema.services;

import edu.school21.cinema.models.CinemaUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public interface CinemaUserService extends UserDetailsService {
    static final String TOKEN_ID = "cinemaUser";

    Optional<CinemaUser> signUp(CinemaUser entity);

    Optional<CinemaUser> signIn(String email, String password);

    Optional<CinemaUser> save(CinemaUser entity);

    Optional<CinemaUser> findByEmail(String email);

    static void setToSession(HttpSession httpSession, CinemaUser administrator) {
        httpSession.setAttribute(TOKEN_ID, administrator);
    }

    static CinemaUser getFromSession(HttpSession httpSession) {
        return (CinemaUser) httpSession.getAttribute(TOKEN_ID);
    }
}

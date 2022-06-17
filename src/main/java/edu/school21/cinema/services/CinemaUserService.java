package edu.school21.cinema.services;

import edu.school21.cinema.models.CinemaUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public interface CinemaUserService extends UserDetailsService {
    final String TOKEN_ID = "cinemaUser";

    Optional<CinemaUser> signUp(CinemaUser entity);

    Optional<CinemaUser> signIn(String email, String password);

    static void setToSession(HttpSession httpSession, CinemaUser cinemaUser) {
        httpSession.setAttribute(TOKEN_ID, cinemaUser);
    }

    static CinemaUser getFromSession(HttpSession httpSession) {
        return (CinemaUser) httpSession.getAttribute(TOKEN_ID);
    }

    static void removeFromSession(HttpSession httpSession) {
        httpSession.removeAttribute(TOKEN_ID);
    }
}

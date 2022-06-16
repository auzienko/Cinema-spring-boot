package edu.school21.cinema.services;

import edu.school21.cinema.models.Administrator;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public interface AdministratorService {
    static final String TOKEN_ID = "cinemaAdmin";

    Optional<Administrator> signUp(Administrator entity);

    Optional<Administrator> signIn(String email, String password);

    static void setToSession(HttpSession httpSession, Administrator administrator) {
        httpSession.setAttribute(TOKEN_ID, administrator);
    }

    static Administrator getFromSession(HttpSession httpSession) {
        return (Administrator) httpSession.getAttribute(TOKEN_ID);
    }

    static void removeFromSession(HttpSession httpSession) {
        httpSession.removeAttribute(TOKEN_ID);
    }
}

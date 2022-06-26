package edu.school21.cinema.services.utils;

import edu.school21.cinema.models.CinemaUser;

public interface EmailConfirmationService {
    void createVerificationToken(String token, CinemaUser user);
    CinemaUser findUserByToken(String token);
}

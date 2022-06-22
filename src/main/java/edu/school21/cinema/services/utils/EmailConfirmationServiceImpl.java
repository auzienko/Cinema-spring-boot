package edu.school21.cinema.services.utils;

import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.models.utils.EmailConfirmation;
import edu.school21.cinema.repositories.utils.EmailConfirmationRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailConfirmationServiceImpl implements EmailConfirmationService {

    private EmailConfirmationRepository emailConfirmationRepository;

    public EmailConfirmationServiceImpl(EmailConfirmationRepository emailConfirmationRepository) {
        this.emailConfirmationRepository = emailConfirmationRepository;
    }

    @Override
    public void createVerificationToken(String token, CinemaUser user) {
        emailConfirmationRepository.save(new EmailConfirmation(token, user));
    }
}

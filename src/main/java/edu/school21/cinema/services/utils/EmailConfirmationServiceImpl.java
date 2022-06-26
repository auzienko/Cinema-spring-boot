package edu.school21.cinema.services.utils;

import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.models.utils.EmailConfirmation;
import edu.school21.cinema.repositories.utils.EmailConfirmationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public CinemaUser findUserByToken(String token) {
        Optional<EmailConfirmation> optionalEmailConfirmation = emailConfirmationRepository.findByToken(token);
        return optionalEmailConfirmation.isPresent() ? optionalEmailConfirmation.get().getCinemaUser() : null;
    }
}

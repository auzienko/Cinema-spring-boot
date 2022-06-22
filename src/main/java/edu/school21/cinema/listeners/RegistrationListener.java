package edu.school21.cinema.listeners;

import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.services.utils.EmailConfirmationService;
import edu.school21.cinema.services.utils.EmailService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final EmailConfirmationService emailConfirmationService;
    private final EmailService emailService;

    public RegistrationListener(EmailConfirmationService emailConfirmationService, EmailService emailService) {
        this.emailConfirmationService = emailConfirmationService;
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        CinemaUser user = event.getUser();
        String token = UUID.randomUUID().toString();
        emailConfirmationService.createVerificationToken(token, user);
        emailService.sendConfirmationLink(user.getEmail(), event.getLocale(), event.getAppUrl() + token);
    }
}

package edu.school21.cinema.services.utils;

import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final MessageSource messageSource;

    public EmailServiceImpl(JavaMailSender mailSender, MessageSource messageSource) {
        this.mailSender = mailSender;
        this.messageSource = messageSource;
    }

    @Override
    public void sendConfirmationLink(String to, Locale locale, String link) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(to);
            helper.setSubject(messageSource.getMessage("email.confirmation.subject", null, locale));
            boolean html = true;
            String messageText = String.format(
                    messageSource.getMessage("email.confirmation.text", null, locale),
                    link);
            helper.setText(messageText, html);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

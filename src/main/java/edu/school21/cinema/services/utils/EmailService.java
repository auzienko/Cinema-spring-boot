package edu.school21.cinema.services.utils;

import java.util.Locale;

public interface EmailService {
    void sendConfirmationLink(String to, Locale locale, String link);
}

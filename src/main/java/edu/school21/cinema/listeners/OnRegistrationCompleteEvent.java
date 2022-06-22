package edu.school21.cinema.listeners;

import edu.school21.cinema.models.CinemaUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private final CinemaUser user;
    private final String appUrl;
    private final Locale locale;

    public OnRegistrationCompleteEvent(CinemaUser user, Locale locale, String appUrl){
        super(user);
        this.user = user;
        this.appUrl = appUrl;
        this.locale = locale;
    }
}

package edu.school21.cinema.controllers;

import edu.school21.cinema.listeners.OnRegistrationCompleteEvent;
import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.models.UserAuthHistory;
import edu.school21.cinema.services.CinemaUserService;
import edu.school21.cinema.services.UserAuthHistoryService;
import edu.school21.cinema.services.utils.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/signUp")
public class SignUp {

    @Value("${school21.send.email.signup}")
    private boolean sendMail;
    private final CinemaUserService cinemaUserService;
    private final ApplicationEventPublisher eventPublisher;
    private final MessageSource messageSource;
    private final UserAuthHistoryService userAuthHistoryService;

    public SignUp(CinemaUserService cinemaUserService,
                  EmailService emailService,
                  ApplicationEventPublisher eventPublisher,
                  MessageSource messageSource,
                  UserAuthHistoryService userAuthHistoryService) {
        this.cinemaUserService = cinemaUserService;
        this.eventPublisher = eventPublisher;
        this.messageSource = messageSource;
        this.userAuthHistoryService = userAuthHistoryService;
    }

    @GetMapping
    public String getPage(@ModelAttribute(name = "userForm") CinemaUser cinemaUser) {
        return "signUp";
    }

    @PostMapping
    public String postPage(@ModelAttribute(name = "userForm") @Valid CinemaUser inputData,
                           BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "signUp";
        }

        Optional<CinemaUser> cinemaUser = Optional.empty();
        if (inputData != null) {
            cinemaUser = cinemaUserService.signUp(inputData);
        }
        if (cinemaUser.isPresent()) {
            String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                    .replacePath(null)
                    .build()
                    .toUriString();
            userAuthHistoryService.save(new UserAuthHistory(LocalDateTime.now(), request.getRemoteAddr(), cinemaUser.get()));
            if (sendMail) {
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent(cinemaUser.get(),
                        LocaleContextHolder.getLocale(), baseUrl + "/confirm/"));
            }
            return "redirect:/confirm";
        }
        model.addAttribute("error",
                messageSource.getMessage("signup.error.cantCreateUser", null, LocaleContextHolder.getLocale()));
        return "signUp";
    }
}

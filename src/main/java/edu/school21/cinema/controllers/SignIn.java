package edu.school21.cinema.controllers;

import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.models.UserStatus;
import edu.school21.cinema.services.CinemaUserServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping("/signIn")
public class SignIn {
    private final CinemaUserServiceImpl cinemaUserService;
    private final MessageSource messageSource;

    public SignIn(CinemaUserServiceImpl cinemaUserService, MessageSource messageSource) {
        this.cinemaUserService = cinemaUserService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public String getPage() {
        return "signIn";
    }

    @PostMapping()
    public String postPage(@ModelAttribute("cinemaUser") CinemaUser inputData, Model model) {
        Optional<CinemaUser> cinemaUser = Optional.empty();
        if (inputData != null) {
            cinemaUser = cinemaUserService.signIn(inputData.getEmail(), inputData.getPassword());
        }
        if (cinemaUser.isPresent()) {
            if (cinemaUser.get().getStatus() == UserStatus.NOT_CONFIRMED) {
                model.addAttribute("error",
                        messageSource.getMessage("signin.error.userEmailIsNotConfirmed", null, LocaleContextHolder.getLocale()));
            }
        } else {
            model.addAttribute("error",
                    messageSource.getMessage("signin.error.wrongEmailOrPassword", null, LocaleContextHolder.getLocale()));
        }
        return "signIn";
    }
}

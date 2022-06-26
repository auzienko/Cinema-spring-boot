package edu.school21.cinema.controllers;

import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.models.UserStatus;
import edu.school21.cinema.services.CinemaUserService;
import edu.school21.cinema.services.utils.EmailConfirmationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/confirm")
public class ConfirmEmail {
    private EmailConfirmationService emailConfirmationService;
    private CinemaUserService userService;

    public ConfirmEmail(EmailConfirmationService emailConfirmationService, CinemaUserService userService) {
        this.emailConfirmationService = emailConfirmationService;
        this.userService = userService;
    }

    @GetMapping
    public String get(){
        return "confirm";
    }

    @GetMapping(value = "/{uuid}")
    public String getUuid(@PathVariable("uuid") String uuid, Model model){
        if (uuid != null){
            CinemaUser user = emailConfirmationService.findUserByToken(uuid);
            if (user != null) {
                user.setStatus(UserStatus.CONFIRMED);
                userService.save(user);
                model.addAttribute("success", "success");
            }
        }
        return "confirm";
    }
}

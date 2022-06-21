package edu.school21.cinema.controllers;

import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.services.CinemaUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/signUp")
public class SignUp {
    private final CinemaUserService cinemaUserService;

    public SignUp(CinemaUserService cinemaUserService) {
        this.cinemaUserService = cinemaUserService;
    }

    @GetMapping
    public String getPage(@ModelAttribute(name = "userForm") CinemaUser cinemaUser) {
        return "signUp";
    }

    @PostMapping
    public String postPage(@ModelAttribute(name = "userForm") @Valid CinemaUser inputData, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "signUp";
        }

        Optional<CinemaUser> cinemaUser = Optional.empty();
        if (inputData != null) {
            cinemaUser = cinemaUserService.signUp(inputData);
        }
        if (cinemaUser.isPresent()) {
            return "redirect:/profile";
        } else {
            model.addAttribute("error", "Can't create this user!");
        }
        return "signUp";
    }
}

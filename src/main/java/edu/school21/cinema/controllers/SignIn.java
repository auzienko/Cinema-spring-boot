package edu.school21.cinema.controllers;

import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.services.CinemaUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/signIn")
public class SignIn {
    private final CinemaUserServiceImpl cinemaUserService;

    public SignIn(CinemaUserServiceImpl cinemaUserService) {
        this.cinemaUserService = cinemaUserService;
    }

    @GetMapping
    public ModelAndView getPage() {
        return new ModelAndView("signIn");
    }

    @PostMapping
    public ModelAndView postPage(@ModelAttribute("cinemaUser") CinemaUser inputData) {
        Optional<CinemaUser> cinemaUser = Optional.empty();
        if (inputData != null) {
            cinemaUser = cinemaUserService.signIn(inputData.getEmail(), inputData.getPassword());
        }
        ModelAndView mv = new ModelAndView();
        if (cinemaUser.isPresent()) {
            mv.setViewName("redirect:/profile");
        } else {
            mv.addObject("error", "Wrong email or password!");
            mv.setViewName("signIn");
        }
        return mv;
    }
}

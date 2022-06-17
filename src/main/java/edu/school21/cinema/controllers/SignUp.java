package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Administrator;
import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.services.AdministratorService;
import edu.school21.cinema.services.CinemaUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/signUp")
public class SignUp {
    private final CinemaUserService cinemaUserService;

    public SignUp(CinemaUserService cinemaUserService) {
        this.cinemaUserService = cinemaUserService;
    }

    @GetMapping
    public ModelAndView getPage() {
        return new ModelAndView("signUp");
    }

//    @PostMapping
//    public ModelAndView postPage(@re) {
//        String email = req.getParameter("email");
//        String password = req.getParameter("password");
//        Optional<Administrator> user = administratorService.signUp(new Administrator(email, password));
//        ModelAndView mv = new ModelAndView();
//        if (user.isPresent()) {
//            mv.setViewName("redirect:/admin/panel");
//            AdministratorService.setToSession(req.getSession(), user.get());
//        } else {
//            mv.addObject("error", "Can't create this user!");
//            mv.setViewName("signUp");
//        }
//        return mv;
//    }

    @PostMapping
    public ModelAndView postPage(@ModelAttribute("cinemaUser") CinemaUser inputData) {
        Optional<CinemaUser> cinemaUser = Optional.empty();
        if (inputData != null) {
            cinemaUser = cinemaUserService.signUp(inputData);
        }
        ModelAndView mv = new ModelAndView();
        if (cinemaUser.isPresent()) {
            mv.setViewName("redirect:/profile");
        } else {
            mv.addObject("error", "Can't create this user!");
            mv.setViewName("signUp");
        }
        return mv;
    }
}

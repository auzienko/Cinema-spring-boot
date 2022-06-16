package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Administrator;
import edu.school21.cinema.services.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/signin")
public class SignIn {
    private AdministratorService administratorService;

    @Autowired
    public SignIn(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }
    @GetMapping
    public ModelAndView getPage() {
        return new ModelAndView("/signin");
    }

    @PostMapping
    public ModelAndView postPage(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<Administrator> user = administratorService.signIn(email, password);
        ModelAndView mv = new ModelAndView();
        if (user.isPresent()) {
            mv.setViewName("redirect:/admin/panel");
            AdministratorService.setToSession(req.getSession(), user.get());
        } else {
            mv.addObject("error", "Wrong email or password!");
            mv.setViewName("/signin");
        }
        return mv;
    }
}

package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/sessions")
public class Sessions {
    private SessionService sessionService;

    @Autowired
    public Sessions(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public String getPage() {
        return "sessions";
    }

    @GetMapping("{id}")
    public ModelAndView getPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("session");
        Optional<Session> sessionOptional = sessionService.get(id);
        if (sessionOptional.isPresent()){
            modelAndView.addObject("sessionInfo", sessionOptional.get());
        }
        return modelAndView;
    }
}

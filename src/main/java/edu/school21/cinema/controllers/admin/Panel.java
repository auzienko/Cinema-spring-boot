package edu.school21.cinema.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/panel")
public class Panel {
    @GetMapping
    public ModelAndView getPage(){
        return new ModelAndView("/admin/panel");
    }
}

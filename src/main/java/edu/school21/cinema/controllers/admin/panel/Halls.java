package edu.school21.cinema.controllers.admin.panel;


import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.models.MovieHall;
import edu.school21.cinema.services.CinemaUserService;
import edu.school21.cinema.services.MovieHallService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller("adminPanelHalls")
@RequestMapping("/admin/panel/halls")
public class Halls {
    private final String PAGE_PATH = "/admin/panel/halls";
    private MovieHallService movieHallService;
    private CinemaUserService cinemaUserService;

//    @Autowired
//    public Halls(MovieHallService movieHallService) {
//        this.movieHallService = movieHallService;
//    }

    @GetMapping
    public ModelAndView getPage(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView(PAGE_PATH);
        Optional<CinemaUser> administrator = cinemaUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<MovieHall> movieHallList = movieHallService.getAllByAdministratorId(administrator.get().getId());
        if (movieHallList.size() > 0) {
            modelAndView.addObject("movieHallList", movieHallList);
        }
        return modelAndView;
    }

    @PostMapping
    public ModelAndView postPage(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView(PAGE_PATH);
        Optional<CinemaUser> administrator = cinemaUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Integer serialNumber = null;
        Integer seats = null;
        try {
            serialNumber = Integer.parseInt(req.getParameter("serialNumber"));
            seats = Integer.parseInt(req.getParameter("seats"));
        } catch (Exception e) {
            modelAndView.addObject("error", "❌ Serial number and seats must be numbers!");
            return modelAndView;
        }
        Optional<MovieHall> optionalMovieHall = movieHallService.getBySerialNumber(serialNumber);
        if (optionalMovieHall.isPresent()) {
            modelAndView.addObject("error", "❌ This movie hall (" + serialNumber + ") is already exist!");
            return modelAndView;
        } else {
            movieHallService.add(new MovieHall(serialNumber, seats, administrator.get()));
            modelAndView.setViewName("redirect:" + PAGE_PATH);
        }
        return modelAndView;
    }
}

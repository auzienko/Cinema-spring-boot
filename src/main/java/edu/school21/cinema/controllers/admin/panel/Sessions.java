package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.MovieHall;
//import edu.school21.cinema.services.AdministratorService;
import edu.school21.cinema.services.CinemaUserService;
import edu.school21.cinema.services.MovieHallService;
import edu.school21.cinema.services.MovieService;
import edu.school21.cinema.services.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Controller("adminPanelSessions")
@RequestMapping("/admin/panel/sessions")
public class Sessions {
    private final String PAGE_PATH = "/admin/panel/sessions";
    private final SessionService sessionService;
    private final MovieHallService movieHallService;
    private final MovieService movieService;
    private final CinemaUserService cinemaUserService;

//    public Sessions(SessionService sessionService, MovieHallService movieHallService, MovieService movieService) {
//        this.sessionService = sessionService;
//        this.movieHallService = movieHallService;
//        this.movieService = movieService;
//    }

    @GetMapping
    public ModelAndView getPage() {
        ModelAndView modelAndView = new ModelAndView(PAGE_PATH);
        List<Session> sessionList = sessionService.getAll();
        if (sessionList.size() > 0) {
            modelAndView.addObject("sessionList", sessionList);
        }
        List<Movie> movieList = movieService.getAll();
        List<MovieHall> movieHallList = movieHallService.getAll();
        modelAndView.addObject("movieList", movieList);
        modelAndView.addObject("movieHallList", movieHallList);
        if (movieList.size() == 0 || movieHallList.size() == 0) {
            modelAndView.addObject("error", "❌ You have to create a Movie and a Hall!");
        }
        return modelAndView;
    }


    @PostMapping
    public ModelAndView postPage(HttpServletRequest req,
                                 @RequestParam("movie") Long movie_id,
                                 @RequestParam("hall") Long hall_id,
                                 @RequestParam("dateTime") LocalDateTime dateTime,
                                 @RequestParam("cost") Integer cost
                                 ) {
        ModelAndView modelAndView = new ModelAndView("redirect:" + PAGE_PATH);
        Optional<CinemaUser> administrator = cinemaUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<MovieHall> optionalMovieHall = movieHallService.get(movie_id);
        Optional<Movie> optionalMovie = movieService.get(hall_id);
      //  LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        if (optionalMovie.isPresent() && optionalMovieHall.isPresent()){
       //     sessionService.add(new Session(optionalMovie.get(), localDateTime, cost, optionalMovieHall.get(), administrator.get()));
        }
        return  modelAndView;
    }
}

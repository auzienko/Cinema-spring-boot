package edu.school21.cinema.controllers.admin.panel;


import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.models.ImageType;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.Image;
import edu.school21.cinema.services.CinemaUserService;
import edu.school21.cinema.services.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Controller("adminPanelFilms")
@RequestMapping("/admin/panel/films")
public class Films {
    private final String PAGE_PATH = "/admin/panel/films";
    private MovieService movieService;
    private CinemaUserService cinemaUserService;
    private Environment env;

//    @Autowired
//    public Films(MovieService movieService, Environment env, CinemaUserService cinemaUserService) {
//        this.movieService = movieService;
//        this.env = env;
//        this.cinemaUserService = cinemaUserService;
//    }

    @GetMapping
    public ModelAndView getPage (HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView(PAGE_PATH);
        Optional<CinemaUser> administrator = cinemaUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Movie> movieList = movieService.getAllByAdministratorId(administrator.get().getId());
        if (movieList.size() > 0) {
            modelAndView.addObject("movieList", movieList);
        }
        return modelAndView;
    }

    @PostMapping()
    public ModelAndView postPage( HttpServletRequest req,
                                 @ModelAttribute("Movie") Movie inputData,
                                 @RequestParam("posterFile") MultipartFile posterFile
    ){
        ModelAndView modelAndView = new ModelAndView(PAGE_PATH);

        Optional<CinemaUser> administrator = cinemaUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (inputData.getTitle() == null) {
            modelAndView.addObject("error", "❌ Title can't be empty!");
            return modelAndView;
        }

        if (inputData.getDescription() == null) {
            modelAndView.addObject("error", "❌ Description can't be empty!");
            return modelAndView;
        }
        Integer yOF = null;
        Integer aR = null;
        try {
            yOF = Integer.parseInt(req.getParameter("yearOfRelease"));
            aR = Integer.parseInt(req.getParameter("ageRestrictions"));
        } catch (Exception e) {
            modelAndView.addObject("error", "❌ Year Of Release and Age Restrictions must be numbers!");
            return modelAndView;
        }

        UUID uuid = UUID.randomUUID();
        try {
            byte[] barr = posterFile.getBytes();
            BufferedOutputStream bufferedOutputStream =
                    new BufferedOutputStream(new FileOutputStream(env.getProperty("storage.path") + "/" + uuid.toString()));
            bufferedOutputStream.write(barr);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.addObject("error", "❌ Can't save poster!");
            return modelAndView;
        }
        Image image = new Image(posterFile.getOriginalFilename(),
                uuid, ImageType.POSTER,  posterFile.getSize(),  posterFile.getContentType(), administrator.get());
        Movie movie = new Movie(inputData.getTitle(), yOF, aR, inputData.getDescription(), image, administrator.get());
        movieService.add(movie);
        modelAndView.setViewName("redirect:" + PAGE_PATH);
        return modelAndView;
    }
}

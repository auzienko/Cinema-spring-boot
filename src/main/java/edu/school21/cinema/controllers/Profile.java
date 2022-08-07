package edu.school21.cinema.controllers;


import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.ImageType;
import edu.school21.cinema.services.CinemaUserService;
import edu.school21.cinema.services.PosterService;
import edu.school21.cinema.services.UserAuthHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;


@AllArgsConstructor
@Controller
@RequestMapping("/profile")
public class Profile {
    private final String PAGE_PATH = "/profile";
    private PosterService posterService;
    private CinemaUserService cinemaUserService;
    private UserAuthHistoryService userAuthHistoryService;
    private Environment env;


    @GetMapping
    public ModelAndView getPage(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView(PAGE_PATH);
        Optional<CinemaUser> user = cinemaUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        System.out.println("My IP (profile) is: " + req.getRemoteAddr());

        if (!user.isPresent()) {
            modelAndView.setViewName("redirect: /");
            return modelAndView;
        }
        Image avatar = user.get().getAvatar();
        modelAndView.addObject( "user", user.get());
        modelAndView.addObject("avatarHistory", posterService.getAllAvatars(user.get().getId(), avatar == null ? null : avatar.getType()));
        modelAndView.addObject("authHistory", userAuthHistoryService.getUserAuthHistory(user.get().getId()));
        if (avatar != null){
            modelAndView.addObject("avatar", "../../images/" + avatar.getFileNameUUID());
        }
        else{
            modelAndView.addObject("avatar", "https://html-online.com/editor/images/html-editor.png");
        }
        return modelAndView;
    }

    @PostMapping()
    public ModelAndView postChat(HttpServletRequest req,
                                 @RequestParam("avatarFile") MultipartFile avatarFile) {
        ModelAndView modelAndView = new ModelAndView(PAGE_PATH);

        Optional<CinemaUser> user = cinemaUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        UUID uuid = UUID.randomUUID();
        try {
            byte[] barr = avatarFile.getBytes();
            InputStream is = new ByteArrayInputStream(barr);
            BufferedImage newBi = ImageIO.read(is);
            ImageIO.write(newBi, "jpeg", new File(env.getProperty("storage.path") + "/" + uuid.toString()));
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.addObject("error", "❌ Can't save avatar!");
            return modelAndView;
        }
        Image avatar = new Image(avatarFile.getOriginalFilename(),
                uuid, ImageType.AVATAR,  avatarFile.getSize(), avatarFile.getContentType(), user.get());
        posterService.add(avatar);
        user.get().setAvatar(avatar);
        cinemaUserService.save(user.get());
        modelAndView.setViewName("redirect:" + PAGE_PATH);
        return modelAndView;
    }
}

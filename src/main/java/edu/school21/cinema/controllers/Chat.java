package edu.school21.cinema.controllers;


import edu.school21.cinema.models.CinemaUser;
import edu.school21.cinema.models.Message;
import edu.school21.cinema.services.CinemaUserService;
import edu.school21.cinema.services.MessageService;
import edu.school21.cinema.services.MovieService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Controller
public class Chat {
    private final MessageService messageService;
    private final CinemaUserService cinemaUserService;
    private final MovieService movieService;


    @GetMapping(value = "/films/{id}/chat")
    public ModelAndView getChat(@PathVariable("id") Long id, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ModelAndView chatMVC = new ModelAndView("/chat");
        chatMVC.addObject("movie",  movieService.get(id).get());
        chatMVC.addObject("user", cinemaUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get());
        List<Message> hist = messageService.getHistory(id);
        Collections.reverse(hist);
        chatMVC.addObject("history", hist);
        return chatMVC;
    }

    @MessageMapping("/films/{id}/chat")
    @SendTo("/films")
    public Message send(@DestinationVariable("id") Long id, String json, @Payload Message message) {
        JSONObject obj = new JSONObject(json);
        CinemaUser sender = cinemaUserService.findByEmail(obj.getString("email")).get();
        message.setAuthor(sender);
        if (sender.getAvatar() != null)
            message.setUUID(sender.getAvatar().getFileNameUUID().toString());
        message.setMovie(movieService.get(obj.getLong("film_id")).get());
        messageService.add(message);
        return message;
    }
}

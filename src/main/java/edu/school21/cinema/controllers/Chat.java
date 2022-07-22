package edu.school21.cinema.controllers;


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
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class Chat {
    private final MessageService messageService;
    private final CinemaUserService cinemaUserService;
    private final MovieService movieService;


    @MessageMapping("/admin/panel/films/${id}/chat")
    @SendTo("/films")
    public Message send(@DestinationVariable("id") Long id, String json, @Payload Message message) {
        JSONObject obj = new JSONObject(json);
        message.setAuthor(cinemaUserService.findByEmail(obj.getString("email")).get());
        message.setMovie(movieService.get(obj.getLong("film_id")).get());
        messageService.add(message);
        return message;
    }
}

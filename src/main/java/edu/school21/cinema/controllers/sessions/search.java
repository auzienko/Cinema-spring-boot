package edu.school21.cinema.controllers.sessions;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/sessions/search")
public class search {
    private SessionService sessionService;

    @Autowired
    public search(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping()
    @ResponseBody
    public Map<String, List<Session>> getPage(@RequestParam("filmName") String filmName) {
        Map<String, List<Session>> result = new HashMap<>();
        result.put("sessions", sessionService.searchByTitle(filmName));
        return result;
    }
}

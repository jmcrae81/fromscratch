package com.mcrae.app.controller;
import com.mcrae.app.entity.Account;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/session")
public class SessionController {
    @GetMapping("/create")
    public String createSession(@RequestBody Account user, HttpSession session){
        String username = user.getUsername();
        String password = user.getPassword();

        session.setAttribute("username", username);
        String sessionId = session.getId();
        return sessionId;
    }

    @GetMapping("/get")
    public String getSession(HttpSession session){
        String username = (String) session.getAttribute("username");

        if(username == null){
            return "No session found!";
        }

        return "Session found with username: " + username;
    }

    @GetMapping("invalidate")
    public String invalidateSession(HttpSession session){
        session.invalidate();
        return "Session invalidated!";
    }
}

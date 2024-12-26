package com.mcrae.app.controller;

import com.mcrae.app.entity.Account;
import com.mcrae.app.service.AccountService;
import com.mcrae.app.security.JwtTokenUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final AccountService accountService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                          AccountService accountService){
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public String login(@RequestBody Account user, HttpServletResponse response) throws BadCredentialsException {
        String userName = user.getUsername();
        String password = user.getPassword();
        if(!password.equals(null)){
            var authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
            var authentication = authenticationManager.authenticate(authenticationToken);
            var jwt = jwtTokenUtil.generateToken(authentication.getName());

            Cookie cookie = new Cookie("Authentication", jwt);
            cookie.setDomain("localhost");
            cookie.setPath("/");
            response.addCookie(cookie);

            return jwt;
        } else {
            throw new BadCredentialsException("Something's wrong!");
        }
    }
}

package com.mcrae.app.service;
import org.springframework.stereotype.Service;
import com.mcrae.app.entity.AuthDto;

@Service
public class AuthService {
    public boolean authenticate(AuthDto authDto) {
        return authDto.getUsername().equals("username")
                && authDto.getPassword().equals("password");
    }
}

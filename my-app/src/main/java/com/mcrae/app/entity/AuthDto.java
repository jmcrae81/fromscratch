package com.mcrae.app.entity;
import org.bson.types.ObjectId;
public class AuthDto {
    private String username;
    private String password;

    public AuthDto() {
    }

    public AuthDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthDto{" +
                ", username='" + username + '\'' +
                '}';
    }
}

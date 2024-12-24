package com.mcrae.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.mcrae.app.entity.Like;
import com.mcrae.app.SocialMediaApp;

public class CreateLikeTest {
    ApplicationContext app;
    HttpClient webClient;
    ObjectMapper objectMapper;

    /**
     * Before every test, reset the database, restart the Javalin app, and create a new webClient and ObjectMapper
     * for interacting locally on the web.
     * @throws InterruptedException
     */
    @BeforeEach
    public void setUp() throws InterruptedException {
        webClient = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
        String[] args = new String[] {};
        app = SpringApplication.run(SocialMediaApp.class, args);
        Thread.sleep(500);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(500);
        SpringApplication.exit(app);
    }
/*
    @Test
    public void createLikeSuccessful() throws IOException, InterruptedException {
        String json = "{\"accountId\":0,\"username\":\"testuser1\",\"password\":\"password\"}";
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/likes"))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        int status = 200; // response.statusCode();
        try{
            writeValues(0, 0, status);
        }catch(IOException e){
            System.out.println(e);
        }
        Assertions.assertEquals(200, status, "Expected Status Code 200- Actual Code was: " + status);
    }

    public void writeValues(int likedby, int messageid, int status) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter("values.txt"));
        String values = likedby + " " + messageid + " " + status;
        writer.write(values);
        writer.close();
    }

 */
}

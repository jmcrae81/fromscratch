package com.mcrae.app.controller;

import com.mcrae.app.entity.Account;
import com.mcrae.app.entity.Message;
import com.mcrae.app.entity.Like;
import com.mcrae.app.service.AccountService;
import com.mcrae.app.service.MessageService;
import com.mcrae.app.service.LikeService;
import com.mcrae.app.security.SimpleCORSFilter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@ComponentScan(basePackages="com.mcrae")
@RestController
public class SocialMediaController {
    
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    AccountService accountService;
    @Autowired
    MessageService messageService;
    @Autowired
    LikeService likeService;

    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account){
        String username = account.getUsername();
        String password = account.getPassword();
        int responseStatus = 400;

        if(isRealAccount(account)){
            responseStatus = 409;
            
        }else if (!username.equals("") && (password.length() > 4)){
                int id = 0;
                Account returnedAccount = accountService.persistAccount(account);
                responseStatus = 200;

                return ResponseEntity.status(responseStatus).body(returnedAccount);
        } 
        return ResponseEntity.status(responseStatus).body(null);        
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        int responseStatus = 401;

        String username = account.getUsername();
        String password = account.getPassword();

        Account accountToReturn = accountService.getAccountByUsernameAndPassword(username, password);
        if(accountToReturn != null){
            responseStatus = 200;
        }

        return ResponseEntity.status(responseStatus).body(accountToReturn);
        
    }


    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        int responseStatus = 400;
        int accountPostedBy = message.getPostedBy();
        String text = message.getMessageText();

        if(isValidId(accountPostedBy)){
            if( (!text.equals("")) && (text.length() <= 255)){
               Message messageToReturn = messageService.persistMessage(message);
               responseStatus = 200;

               return ResponseEntity.status(responseStatus).body(messageToReturn);
            }
        }

        return ResponseEntity.status(responseStatus).body(null);

    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> retrieveAllMessages(){
        int responseStatus = 200; 
        List<Message> allCurrentMessages = new ArrayList<Message>();
        allCurrentMessages = messageService.retrieveAllMessages();

        return ResponseEntity.status(responseStatus).body(allCurrentMessages);
        
    }
    
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> retrieveMessageById(@PathVariable int messageId){
        int responseStatus = 200;
        Message requestedMessage = messageService.retrieveMessageById(messageId);

        return ResponseEntity.status(responseStatus).body(requestedMessage);
    }
    
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId){
        int numberOfRowsUpdated = 0;
        int responseStatus = 200;

        Message messageToDelete = messageService.retrieveMessageById(messageId);
        
        if(messageToDelete != null){
            messageService.deleteMessage(messageToDelete);
            numberOfRowsUpdated = 1;

            return ResponseEntity.status(responseStatus).body(numberOfRowsUpdated);
        }
        
        return ResponseEntity.status(responseStatus).body(null);
    }
    
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageText(@PathVariable int messageId,
            @RequestBody Map<String, String> textFromRequestBody){
        int responseStatus = 400;
        String extractedText = textFromRequestBody.get("messageText");
        Message messageToUpdate = null;
            
        if(isValidId(messageId)){
            messageToUpdate = messageService.retrieveMessageById(messageId);
        }

        if(messageToUpdate != null){
            if( (extractedText.length() <= 255) && (extractedText.length() > 0)){
                    int numberOfRowsUpdated = 1;
                    responseStatus = 200;
                    messageService.updateMessageText(messageToUpdate, extractedText);
                    
                    return ResponseEntity.status(responseStatus).body(numberOfRowsUpdated);
            }
        }

        return ResponseEntity.status(responseStatus).body(null);        
    }    

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccountId(@PathVariable int accountId){
        int responseStatus = 200;
        int postedBy = accountId;
        List<Message> userMessages =  new ArrayList<Message>();
        userMessages = messageService.getMessagesFromPostedBy(postedBy);
            
        return ResponseEntity.status(responseStatus).body(userMessages);
    }

    @GetMapping("/findFriend/{username}")
    public ResponseEntity<Account> findByUsername(@PathVariable String username){
        int responseStatus = 200;
        Account foundAccount = accountService.findByUsername(username);

        if(foundAccount == null){
            responseStatus = 404;
        }
        return ResponseEntity.status(responseStatus).body(foundAccount);
    }

    @PostMapping("/likes")
    public ResponseEntity<Like> createLike(@RequestBody Account accountThatLiked){
        int likedBy = accountThatLiked.getAccountId();
        int responseStatus = 200;

        try{
            writeValues(accountThatLiked + " " + responseStatus);
        }catch (IOException e){
            System.out.println(e);
        }

        Like newLike = likeService.likeMessage(likedBy, 33);

        return ResponseEntity.status(200).body(newLike);
    }

    public boolean isValidId(int id){
        List<Account> validUsers = new ArrayList<Account>();
        validUsers = accountService.getAllAccounts();
        int currentUserId = 0;

        for( Account user : validUsers){
            currentUserId = user.getAccountId();
            if(currentUserId == id){
                return true;
            }
        }
        
        return false;
    }

    public boolean isRealAccount(Account account){
        List<Account> currentUsers = new ArrayList<Account>();
        currentUsers = accountService.getAllAccounts();
        String username = account.getUsername();
        String compareTo = new String();

        if(currentUsers.isEmpty()){
            return false;
        }

        for( Account current : currentUsers){
            compareTo = current.getUsername();
            if( username.equals(compareTo) ){
                return true;
            }
        }

        return false;
    }

    public void writeValues(String values) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter("string.txt"));

        writer.write(values);
        writer.close();
    }

}


package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.*;
import com.example.entity.*;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
@RequestMapping
public class SocialMediaController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private MessageService messageService;
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> messages = messageService.getAllMessages();
        return new ResponseEntity<>(messages,HttpStatus.OK);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        if(message.getMessageText()!="" && message.getMessageText().length()<255 && accountService.idExists(message.getPostedBy())){
            Message createdMessage =messageService.createMessage(message);
            return new ResponseEntity<>(createdMessage, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }

    @PostMapping("/register")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        if(account.getUsername()==""){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(accountService.usernameExists(account.getUsername())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Account createdAccount =accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account){
        if(!accountService.passwordExists(account.getPassword())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Account checkExists = accountService.findByUsername(account.getUsername());
        if(checkExists==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(checkExists, HttpStatus.OK);
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer message_id){
        Message message = messageService.findByID(message_id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer message_id){
        if(!messageService.messageExists(message_id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        Integer rows = messageService.deleteMessage(message_id);
        return new ResponseEntity<>(rows,HttpStatus.OK);
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable Integer message_id, @RequestBody Message message){
        if (!messageService.messageExists(message_id) || message.getMessageText().length()>255 || message.getMessageText().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Integer rows = messageService.updateMessage(message_id, message.getMessageText());
        return new ResponseEntity<>(rows,HttpStatus.OK);
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByPostedBy(@PathVariable Integer account_id){
        List<Message> messages = messageService.getAllMessagesbyUserId(account_id);
        return new ResponseEntity<>(messages, HttpStatus.OK);

    }

}

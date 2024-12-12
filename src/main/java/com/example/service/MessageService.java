package com.example.service;
import com.example.repository.*;
import com.example.entity.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }
    public Message createMessage(Message message){
  
        return messageRepository.save(new Message(message.getPostedBy(), message.getMessageText(), message.getTimePostedEpoch()));
    }

    public Message findByID(Integer ID){
        return messageRepository.findBymessageId(ID);
    }

    public Integer deleteMessage(Integer ID){
        return messageRepository.deleteByMessageId(ID);
    }

    public boolean messageExists(Integer ID){
        return messageRepository.existsById(ID);
    }

    public Integer updateMessage(Integer ID, String messageText){
        return messageRepository.updateMessageId(ID, messageText);
    }

    public List<Message> getAllMessagesbyUserId(Integer postedBy){
        return messageRepository.getAllMessagesByUserId(postedBy);
    }

}

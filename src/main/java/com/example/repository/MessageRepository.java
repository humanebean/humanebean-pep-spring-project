package com.example.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.example.entity.*;
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    public Message findBymessageId(Integer ID);

    @Modifying
    @Transactional
    @Query("DELETE FROM Message m WHERE m.messageId = :messageId")
    public Integer deleteByMessageId(@Param("messageId") Integer messageId);

    public boolean existsByMessageId(Integer messageId);

    @Modifying
    @Transactional
    @Query("UPDATE Message m SET m.messageText = :messageText WHERE m.messageId = :messageId")
    public Integer updateMessageId(@Param("messageId") Integer messageId, @Param("messageText") String messageText);
    
    @Query("SELECT m FROM Message m where m.postedBy = :postedBy")
    public List<Message> getAllMessagesByUserId(@Param("postedBy") Integer postedBy);
    

}

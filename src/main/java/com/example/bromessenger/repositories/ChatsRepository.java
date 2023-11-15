package com.example.bromessenger.repositories;

import com.example.bromessenger.model.Chat;
import com.example.bromessenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatsRepository extends JpaRepository<Chat,Long> {
    @Query("SELECT c FROM Chat c JOIN c.chatMembers m WHERE m.user = :user OR m.user = :friend")
    List<Chat> findChatsByMembers(@Param("user") User user, @Param("friend") User friend);

}

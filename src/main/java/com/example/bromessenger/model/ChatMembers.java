package com.example.bromessenger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ChatMembers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_member_id")
    private Long chatMemberId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "chat_id")
    private Chats chat;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "is_admin")
    private boolean isAdmin;

}

package com.example.bromessenger.model;
import com.example.bromessenger.model.enums.TypeChat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private TypeChat typeChat;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.MERGE)
    private List<ChatMember> chatMembers = new ArrayList<>();

    @OneToMany(mappedBy = "chat", cascade = CascadeType.MERGE)
    private List<Message> messages = new ArrayList<>();
    public Chat(Long id) {
        this.id = id;
    }

}

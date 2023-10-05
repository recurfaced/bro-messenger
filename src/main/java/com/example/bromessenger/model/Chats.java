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
public class Chats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeChat")
    private TypeChat typeChat;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.MERGE)
    private List<ChatMembers> chatMembers = new ArrayList<>();

    @OneToMany(mappedBy = "chat", cascade = CascadeType.MERGE)
    private List<Messages> messages = new ArrayList<>();

}

package com.example.bromessenger.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "friendId")
    private Long friendId;
}

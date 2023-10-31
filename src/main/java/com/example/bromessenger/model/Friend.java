package com.example.bromessenger.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private Long friendId;
}

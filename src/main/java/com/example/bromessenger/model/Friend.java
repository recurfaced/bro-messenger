package com.example.bromessenger.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long friendId;

    private boolean accept;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public Friend(Long friendId, boolean accept, User user) {
        this.friendId = friendId;
        this.accept = accept;
        this.user = user;
    }
}

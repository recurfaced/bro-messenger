package com.example.bromessenger.model.dto;
import com.example.bromessenger.model.Message;
import com.example.bromessenger.model.Notice;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserAuthDto {

    private Long id;

    private String username;

    private LocalDateTime last_login;

    private boolean status;

    private int friends;

    private List<Message> messages;

    private List<Notice> notices;

}

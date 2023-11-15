package com.example.bromessenger.web.resonse;

import com.example.bromessenger.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListFriendsResponse {
    private Long id;
    private Map<Long, String> getFriendsListById;
}

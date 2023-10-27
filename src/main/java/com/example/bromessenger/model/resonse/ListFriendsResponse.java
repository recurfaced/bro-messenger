package com.example.bromessenger.model.resonse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListFriendsResponse {
    private Map<Long, String> getFriendsListById;
}

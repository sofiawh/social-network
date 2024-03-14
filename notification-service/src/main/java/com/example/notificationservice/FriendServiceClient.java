package com.example.notificationservice;



import com.example.notificationservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "friend-service", url = "http://localhost:8078")
public interface FriendServiceClient {

    @GetMapping("/api/friend/profiles/all")
    List<UserDTO> getAllFriendsProfile(@RequestHeader("id") String userId);
}


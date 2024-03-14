package com.nabilaitnacer.servicepost.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostPlacedEvent {
    private String postNumber;
    private String userId;

}

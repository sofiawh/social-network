package com.nabilaitnacer.servicepost.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPlacedEvent {
    private String postNumber;
}

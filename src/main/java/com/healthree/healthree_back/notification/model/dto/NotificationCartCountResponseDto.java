package com.healthree.healthree_back.notification.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationCartCountResponseDto {
    private Long notificationCount;
    private Long cartCount;
}

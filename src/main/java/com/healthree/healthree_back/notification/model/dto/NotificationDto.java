package com.healthree.healthree_back.notification.model.dto;

import com.healthree.healthree_back.notification.model.entity.NotificationEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDto {
    private Long id;
    private Long userId;
    private String content;
    private Boolean isRead;

    public NotificationDto(NotificationEntity userEntity) {
        this.id = userEntity.getId();
        this.userId = userEntity.getUserId();
        this.content = userEntity.getContent();
        this.isRead = userEntity.getIsRead();
    }
}

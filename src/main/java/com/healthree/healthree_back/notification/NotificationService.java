package com.healthree.healthree_back.notification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.notification.model.dto.NotificationDto;
import com.healthree.healthree_back.notification.model.dto.NotificationResponseDto;
import com.healthree.healthree_back.notification.model.entity.NotificationEntity;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationService {
    private NotificationRepository notificationRepository;

    @Transactional(readOnly = false)
    public NotificationResponseDto getNotifications(UserEntity userEntity, PageRequestDto pageRequestDto) {
        Slice<NotificationEntity> notifications = notificationRepository.findByUserId(userEntity.getId(),
                pageRequestDto.getPageable());

        List<NotificationDto> notificationDtos = new ArrayList<>();

        notifications.getContent().forEach(notification -> {
            notificationDtos.add(new NotificationDto(notification));

            notification.setIsRead(Boolean.TRUE);
        });

        notificationRepository.saveAll(notifications.getContent());

        String nextUrl = null;
        if (notifications.hasNext()) {
            nextUrl = "/app/notification" + pageRequestDto.nextUrl();
        }

        return new NotificationResponseDto(notificationDtos, nextUrl);
    }
}

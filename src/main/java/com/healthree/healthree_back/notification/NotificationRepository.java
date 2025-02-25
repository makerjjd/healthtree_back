package com.healthree.healthree_back.notification;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.healthree.healthree_back.notification.model.entity.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    Slice<NotificationEntity> findByUserId(Long id, Pageable pageable);

    Long countByUserIdAndIsRead(Long id, Boolean false1);

}

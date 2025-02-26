package com.healthree.healthree_back.order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.healthree.healthree_back.order.model.entity.UserOrderEntity;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {

    Slice<UserOrderEntity> findAllByUserIdOrderByOrderDateTimeDesc(Long id, Pageable pageable);

    Optional<UserOrderEntity> findByIdAndUserId(Long id, Long userId);

    List<UserOrderEntity> findTop3ByUserIdOrderByOrderDateTimeDesc(Long id);

}

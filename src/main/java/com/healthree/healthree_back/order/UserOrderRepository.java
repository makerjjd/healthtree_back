package com.healthree.healthree_back.order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthree.healthree_back.order.model.entity.UserOrderEntity;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {

    Slice<UserOrderEntity> findAllByUserIdOrderByOrderDateTimeDesc(Long id, Pageable pageable);

    Optional<UserOrderEntity> findByIdAndUserId(Long id, Long userId);

    List<UserOrderEntity> findTop3ByUserIdOrderByOrderDateTimeDesc(Long id);

    @Query(value = "SELECT * FROM user_order ORDER BY id DESC", countQuery = "SELECT count(*) FROM user_order ORDER BY id DESC", nativeQuery = true)
    Page<UserOrderEntity> findWithDeleteAll(Pageable pageable);

    @Query(value = "SELECT * FROM user_order WHERE id = :orderId", nativeQuery = true)
    Optional<UserOrderEntity> findWithDeleteById(Long orderId);

}

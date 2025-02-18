package com.healthree.healthree_back.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healthree.healthree_back.my.dto.projection.OrderItemSummaryProjection;
import com.healthree.healthree_back.order.model.dto.projection.OrderItemPorjection;
import com.healthree.healthree_back.order.model.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
    @Query(value = "SELECT oi.id as id, oi.item_id as itemId, oi.quantity as quantity, oi.order_status as orderStatus, si.name as itemName, si.price as price, si.image_url as imageUrl FROM order_item oi LEFT JOIN shopping_item si on si.id = oi.item_id where oi.order_id in (:orderIds)", nativeQuery = true)
    List<OrderItemPorjection> findOrderItemInfosByOrderIds(@Param("orderIds") List<Long> orderIds);

    List<OrderItemEntity> findAllByOrderId(Long id);

    @Query(value = "SELECT si.name as itemName, oi.quantity as quantity, oi.created_at as createdAt, oi.order_status as orderStatus FROM order_item oi LEFT JOIN shopping_item si on si.id = oi.item_id where oi.user_id = :id ORDER BY oi.created_at DESC LIMIT 3", nativeQuery = true)
    List<OrderItemSummaryProjection> findTop3ByUserIdOrderByCreatedAtDesc(Long id);

}

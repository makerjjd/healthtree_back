package com.healthree.healthree_back.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healthree.healthree_back.order.model.dto.projection.OrderItemPorjection;
import com.healthree.healthree_back.order.model.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
    @Query(value = "SELECT oi.order_id as orderId, oi.id as id, oi.count as quantity, si.title as itemName, si.price as price, si.thumbnail as imageUrl FROM order_item oi LEFT JOIN shopping_item si on si.id = oi.item_id where oi.order_id in (:orderIds)", nativeQuery = true)
    List<OrderItemPorjection> findOrderItemInfosByOrderIds(@Param("orderIds") List<Long> orderIds);

    List<OrderItemEntity> findAllByOrderId(Long id);
}

package com.healthree.healthree_back.shopping;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthree.healthree_back.shopping.model.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

}

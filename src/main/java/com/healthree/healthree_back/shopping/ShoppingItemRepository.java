package com.healthree.healthree_back.shopping;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.healthree.healthree_back.shopping.model.entity.ShoppingItemEntity;

public interface ShoppingItemRepository extends JpaRepository<ShoppingItemEntity, Long> {

    List<ShoppingItemEntity> findAllByIdIn(List<Long> productIds);

    Slice<ShoppingItemEntity> findByTitleContaining(String search, Pageable pageable);

}

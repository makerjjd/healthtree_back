package com.healthree.healthree_back.shopping;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.healthree.healthree_back.shopping.model.dto.projection.ShoppingCartItemProjection;
import com.healthree.healthree_back.shopping.model.entity.ShoppingCartEntity;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {
    @Query(value = "SELECT s.id AS id, s.item_id AS shoppingItemId, si.title AS title, si.sub_title AS subTitle, si.thumbnail AS imageUrl, si.discount_price AS price, s.count AS quantity, si.stock AS stock FROM shopping_cart s LEFT JOIN shopping_item si ON s.item_id = si.id WHERE s.user_id = :id", nativeQuery = true, countQuery = "SELECT COUNT(*) FROM shopping_cart s WHERE s.user_id = :id")
    Slice<ShoppingCartItemProjection> findAllShoppintCartProjectionByUserId(Long id, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE shopping_cart SET count = :quantity WHERE user_id = :userId AND item_id = :itemId", nativeQuery = true)
    void updateQuantity(Integer quantity, Long userId, Long itemId);

    Optional<ShoppingCartEntity> findByIdAndUserId(Long id, Long userId);

}

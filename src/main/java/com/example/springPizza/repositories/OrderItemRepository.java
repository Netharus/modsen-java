package com.example.springPizza.repositories;


import com.example.springPizza.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItem.OrderItemId> {
    @Query(nativeQuery = true, value = "SELECT * FROM order_items WHERE order_id = :orderId")
    List<OrderItem> findAllByOrderId(Long orderId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM order_items WHERE order_id = :orderId")
    void deleteByOrderId(Long orderId);
}

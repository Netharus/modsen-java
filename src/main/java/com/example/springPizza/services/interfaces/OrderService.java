package com.example.springPizza.services.interfaces;

import com.example.springPizza.mappers.dtos.OrderRequest;
import com.example.springPizza.mappers.dtos.OrderResponse;
import com.example.springPizza.models.Order;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    List<Order> getALLByUserId(Long userId);
    OrderResponse createOrder(OrderRequest request, UserDetails userDetails);
    OrderResponse updateOrder(Long id, OrderRequest request);
    void deleteOrder(Long id);
}

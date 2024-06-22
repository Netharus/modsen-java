package com.example.springPizza.services.interfaces;

import com.example.springPizza.models.Order;
import com.example.springPizza.mappers.dtos.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long id) throws Exception;
    List<OrderDTO> getALLByUserId(Long userId);
    Order createOrder(OrderDTO orderDTO);
    Order updateOrder(Long id, OrderDTO orderDTO) throws Exception;
    void deleteOrder(Long id);
}

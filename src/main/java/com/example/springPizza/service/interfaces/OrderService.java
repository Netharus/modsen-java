package com.example.springPizza.service.interfaces;

import com.example.springPizza.database.models.Order;
import com.example.springPizza.database.models.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long id) throws Exception;
    List<OrderDTO> getALLByUserId(Long userId);
    Order createOrder(OrderDTO orderDTO);
    Order updateOrder(Long id, OrderDTO orderDTO) throws Exception;
    void deleteOrder(Long id);
}

package com.example.springPizza.utils;

import com.example.springPizza.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SecurityUtil {
    private final OrderService orderService;
    public boolean hasAccessToOrder(Long orderId, String userLogin) {
        return orderService.getOrderById(orderId).getUsername().equals(userLogin);
    }
}

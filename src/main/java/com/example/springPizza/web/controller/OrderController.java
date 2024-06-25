package com.example.springPizza.web.controller;


import com.example.springPizza.mappers.dtos.OrderRequest;
import com.example.springPizza.mappers.dtos.OrderResponse;
import com.example.springPizza.models.Order;
import com.example.springPizza.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pizza/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Order> findAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/{id}")
    public Order findOrderById(@PathVariable(name = "id") Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/{id}")
    public List<Order> findAllOrdersByUserId(@PathVariable(name = "id") Long id){
        return orderService.getALLByUserId(id);
    }

    @PostMapping()
    @PreAuthorize("isAuthenticated()")
    public OrderResponse createOrder(@RequestBody OrderRequest request, @AuthenticationPrincipal UserDetails userDetails){
        return orderService.createOrder(request, userDetails);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public OrderResponse updateOrder(@PathVariable(name = "id") Long id,  @RequestBody OrderRequest request) {
        return orderService.updateOrder(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteOrder(@PathVariable(name = "id") Long id){
        orderService.deleteOrder(id);
    }
}

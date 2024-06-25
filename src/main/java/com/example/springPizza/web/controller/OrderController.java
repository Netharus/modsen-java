package com.example.springPizza.web.controller;


import com.example.springPizza.mappers.dtos.OrderRequest;
import com.example.springPizza.mappers.dtos.OrderResponse;
import com.example.springPizza.database.models.Order;
import com.example.springPizza.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pizza/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

 // for all users

    private final OrderService orderService;

    @GetMapping
    public List<Order> findAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/{id}")
    public Order findOrderById(@PathVariable(name = "id") Long id) throws Exception {
        return orderService.getOrderById(id);
    }

    @GetMapping("/{id}")
    public List<Order> findAllOrdersByUserId(@PathVariable(name = "id") Long id){
        return orderService.getALLByUserId(id);
    }

    @PostMapping()
    public OrderResponse createOrder(@RequestBody OrderRequest request){
        return orderService.createOrder(request);
    }

    @PutMapping("/{id}")
    public OrderResponse updateOrder(@PathVariable(name = "id") Long id,  @RequestBody OrderRequest request) throws Exception {
        return orderService.updateOrder(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable(name = "id") Long id){
        orderService.deleteOrder(id);
    }


}

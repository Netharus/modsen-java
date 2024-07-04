package com.example.springPizza.controllers;


import com.example.springPizza.mappers.dtos.OrderRequest;
import com.example.springPizza.mappers.dtos.OrderResponse;
import com.example.springPizza.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/pizza/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<OrderResponse>> findAllOrders(){
        List<OrderResponse> orderResponseList = orderService.getAllOrders();
        if (orderResponseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(orderResponseList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
//    @PreAuthorize("@securityUtil.hasAccessToOrder(#id, principal.login) || hasAuthority('ADMIN')")
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
//    @PreAuthorize("#id == authentication.principal.id || hasAuthority('ADMIN')")
    public ResponseEntity<List<OrderResponse>> findAllOrdersByUserId(@PathVariable(name = "id") Long id){
        List<OrderResponse> orderResponseList = orderService.getAllByUserId(id);
        if (orderResponseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(orderResponseList, HttpStatus.OK);
        }
    }

//    @PostMapping
////    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request, @AuthenticationPrincipal UserDetails userDetails){
//        return new ResponseEntity<>(orderService.createOrder(request, userDetails), HttpStatus.CREATED);
//    }

    @PutMapping("/{id}")
//    @PreAuthorize("@securityUtil.hasAccessToOrder(#id, principal.login) || hasAuthority('ADMIN')")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable(name = "id") Long id, @RequestBody OrderRequest request) {
        return new ResponseEntity<>(orderService.updateOrder(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("@securityUtil.hasAccessToOrder(#id, principal.login) || hasAuthority('ADMIN')")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable(name = "id") Long id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

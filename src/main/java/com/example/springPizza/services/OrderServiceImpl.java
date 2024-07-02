package com.example.springPizza.services;

import com.example.springPizza.exceptions.OrderNotFoundException;
import com.example.springPizza.mappers.OrderMapper;
import com.example.springPizza.mappers.dtos.OrderRequest;
import com.example.springPizza.mappers.dtos.OrderResponse;
import com.example.springPizza.mappers.dtos.ProductResponse;
import com.example.springPizza.mappers.dtos.UserResponse;
import com.example.springPizza.models.Order;
import com.example.springPizza.models.OrderItem;
import com.example.springPizza.repositories.OrderItemRepository;
import com.example.springPizza.repositories.OrderRepository;
import com.example.springPizza.services.interfaces.OrderService;
import com.example.springPizza.services.interfaces.ProductService;
import com.example.springPizza.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    @Transactional(readOnly = true)
    @Override
    public List<OrderResponse> getAllOrders() {
        return getOrderResponses(orderRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        UserResponse user = userService.getUserById(order.getUserId());
        List<ProductResponse> products = orderItemRepository.findAllByOrderId(order.getId()).stream()
                .map(orderItem -> productService.getProductById(orderItem.getOrderItemId().getProductId()))
                .toList();
        return orderMapper.toResponse(order, user, products);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderResponse> getAllByUserId(Long userId) {
        return getOrderResponses(orderRepository.findAllByUserId(userId));
    }

//    @Override
//    public OrderResponse createOrder(OrderRequest request, UserDetails userDetails) {
//        Order order = orderMapper.toModel(request);
//
//        User user = userRepository.findByLogin(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
//        List<Product> products = productRepository.findAllById(request.getProductIds());
//
//        order.setUserId(user.getId());
//        orderRepository.save(order);
//        return orderMapper.toResponse(order, user, products);
//    }

    @Transactional
    @Override
    public OrderResponse updateOrder(Long id, OrderRequest request) {
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        orderItemRepository.deleteByOrderId(id);
        request.getProductIds()
                .forEach(productId -> orderItemRepository.save(new OrderItem(new OrderItem.OrderItemId(id, productId))));
        orderRepository.save(order);
        UserResponse user = userService.getUserById(order.getId());
        List<ProductResponse> products = orderItemRepository.findAllByOrderId(order.getId()).stream()
                .map(orderItem -> productService.getProductById(orderItem.getOrderItemId().getProductId()))
                .toList();
        return orderMapper.toResponse(order, user, products);
    }

    @Override
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            log.info("Tried delete not existing category");
        }
    }

    private List<OrderResponse> getOrderResponses(List<Order> orders) {
        List<UserResponse> users = orders.stream()
                .map(order -> userService.getUserById(order.getUserId()))
                .toList();
        List<List<OrderItem>> listOrderItems = orders.stream()
                .map(order -> orderItemRepository.findAllByOrderId(order.getId())).toList();
        List<List<ProductResponse>> products = listOrderItems.stream()
                .map(orderItems -> orderItems.stream()
                        .map(orderItem -> productService.getProductById(orderItem.getOrderItemId().getProductId()))
                        .toList())
                .toList();
        return orderMapper.toResponses(orders, users, products);
    }
}

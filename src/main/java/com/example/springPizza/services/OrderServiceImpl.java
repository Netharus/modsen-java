package com.example.springPizza.services;

import com.example.springPizza.exceptions.OrderNotFoundException;
import com.example.springPizza.exceptions.UserNotFoundException;
import com.example.springPizza.mappers.OrderMapper;
import com.example.springPizza.mappers.dtos.OrderRequest;
import com.example.springPizza.mappers.dtos.OrderResponse;
import com.example.springPizza.models.Order;
import com.example.springPizza.models.Product;
import com.example.springPizza.models.User;
import com.example.springPizza.repositories.OrderRepository;
import com.example.springPizza.repositories.ProductRepository;
import com.example.springPizza.repositories.UserRepository;
import com.example.springPizza.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getALLByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public OrderResponse createOrder(OrderRequest request, UserDetails userDetails) {
        Order order = orderMapper.toModel(request);

        User user = userRepository.findByLogin(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        List<Product> products = productRepository.findAllById(request.getProductIds());

        order.setUserId(user.getId());
        orderRepository.save(order);
        return orderMapper.toResponse(order, user, products);
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest request) {
        orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        Order order = orderMapper.toModel(request);
        order.setId(id);
        orderRepository.saveAndFlush(order);
        User user = userRepository.findById(order.getId()).orElseThrow(RuntimeException::new);
        return orderMapper.toResponse(order, user, productRepository.findAllById(request.getProductIds()));
    }

    @Override
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            log.info("Tried delete not existing category");
        }
    }
}

package com.example.springPizza.services;

import com.example.springPizza.mappers.OrderMapper;
import com.example.springPizza.mappers.dtos.OrderRequest;
import com.example.springPizza.mappers.dtos.OrderResponse;
import com.example.springPizza.database.models.Order;
import com.example.springPizza.database.models.Product;
import com.example.springPizza.database.models.User;
import com.example.springPizza.repositories.OrderRepository;
import com.example.springPizza.repositories.ProductRepository;
import com.example.springPizza.repositories.UserRepository;
import com.example.springPizza.services.interfaces.OrderService;
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
        return orderRepository.findOrderById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getALLByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        Order order = orderMapper.toModel(request);
        User user = userRepository.getById(request.getUserId());
        List<Product> products = productRepository.findAllById(request.getProductsId());
        orderRepository.save(order);
        return orderMapper.toResponse(order, user, products);
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest request) throws Exception {
        orderRepository.findById(id).orElseThrow(() -> new Exception("Order not found"));
        Order order = orderMapper.toModel(request);
        order.setId(id);
        orderRepository.saveAndFlush(order);
        return orderMapper.toResponse(order, userRepository.getById(order.getId()), productRepository.findAllById(request.getProductsId()));
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

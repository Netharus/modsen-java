package com.example.springPizza.service;

import com.example.springPizza.database.models.Order;
import com.example.springPizza.repositories.OrderRepository;
import com.example.springPizza.database.models.dto.OrderDTO;
import com.example.springPizza.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) throws Exception {
        return convertToDTO(orderRepository.findById(id).orElseThrow(() -> new Exception("Category not found")));
    }

    @Override
    public List<OrderDTO> getALLByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        Order order = convertFromDTO(orderDTO);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateOrder(Long id, OrderDTO orderDTO) throws Exception {
        orderRepository.findById(id).orElseThrow(() -> new Exception("Order not found"));
        Order order = convertFromDTO(orderDTO);
        return orderRepository.saveAndFlush(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            log.info("Tried delete not existing category");
        }
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(Long.valueOf(order.getUserId()));
        return orderDTO;
    }

    private Order convertFromDTO(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setUserId(Math.toIntExact(orderDTO.getUserId()));
        return order;
    }
}

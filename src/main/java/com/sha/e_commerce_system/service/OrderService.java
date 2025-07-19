package com.sha.e_commerce_system.service;

import com.sha.e_commerce_system.domain.Order;
import com.sha.e_commerce_system.repository.OrderRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    void save(Order order);

    Optional<Order> findById(String id);

    List<Order> findAll();

    String update(String id, Order order);

    String delete(String id);

    List<Order> findByCustomerId(String customerId);

    List<Order> findByCustomerIdAndDateRange(String customerId, String start, String end);

    List<Order> findByStatusAndDateRange(String status, String start, String end);

    List<Order> findByStatus(String status);

    List<OrderRepository.OrderWithProducts> findOrdersWithProductDetailsByCustomerId(String customerId);
}

package com.sha.e_commerce_system.service.impl;

import com.sha.e_commerce_system.domain.Order;
import com.sha.e_commerce_system.repository.OrderRepository;
import com.sha.e_commerce_system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public void save(Order order) {
        String formattedDate = new java.text.SimpleDateFormat("yyyyMMdd").format(new Date());
        order.setOrderedDate(formattedDate);
        repository.save(order);
    }

    @Override
    public Optional<Order> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public String update(String id, Order order) {
        repository.findById(id).ifPresent(v -> {
            v.setStatus(order.getStatus());
            v.setItems(order.getItems());
            v.setCustomerId(order.getCustomerId());
            repository.save(v);
        });
        return "Successfully Updated";
    }

    @Override
    public String delete(String id) {
        Optional<Order> order = repository.findById(id);
        if (order.isPresent()) {
            repository.deleteById(id);
        }
        return "Successfully Deleted";
    }

    @Override
    public List<Order> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    // Find orders by customerId and a date range
    @Override
    public List<Order> findByCustomerIdAndDateRange(String customerId, String start, String end) {
        return repository.findByCustomerIdAndOrderedDateBetween(customerId, start, end);
    }

    // Find orders by status and a date range
    @Override
    public List<Order> findByStatusAndDateRange(String status, String start, String end) {
        return repository.findByStatusAndOrderedDateBetween(status, start, end);
    }

    // Find orders by status only
    @Override
    public List<Order> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    // Use aggregation to get orders with product details for a customer
    @Override
    public List<OrderRepository.OrderWithProducts> findOrdersWithProductDetailsByCustomerId(String customerId) {
        return repository.findOrdersWithProductDetailsByCustomerId(customerId);
    }
}

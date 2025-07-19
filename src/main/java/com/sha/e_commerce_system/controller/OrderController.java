package com.sha.e_commerce_system.controller;

import com.sha.e_commerce_system.domain.Order;
import com.sha.e_commerce_system.repository.OrderRepository;
import com.sha.e_commerce_system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Order order) {
        this.service.save(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Order>> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody Order order) {
        return ResponseEntity.ok(this.service.update(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.service.delete(id));
    }

    @GetMapping("/find-by-customer")
    public ResponseEntity<List<Order>> findByCustomer(@RequestParam("customerId") String customerId) {
        return ResponseEntity.ok(this.service.findByCustomerId(customerId));
    }

    @GetMapping("/find-by-customer-and-date/{id}")
    public ResponseEntity<List<Order>> findByCustomerIdAndDateRange(@PathVariable("id") String id, @RequestParam("startDate") String startDate,
                                                                    @RequestParam("endDate") String endDate) {
     return ResponseEntity.ok(this.service.findByCustomerIdAndDateRange(id, startDate, endDate));
    }

    @GetMapping("/find-by-status-and-date")
    public ResponseEntity<List<Order>> findByStatusAndDateRange(@RequestParam("status") String status, @RequestParam("startDate") String startDate,
                                                                    @RequestParam("endDate") String endDate) {
        return ResponseEntity.ok(this.service.findByStatusAndDateRange(status, startDate, endDate));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderRepository.OrderWithProducts>> findOrdersWithProductDetailsByCustomerId(@PathVariable("id")String id){
        return ResponseEntity.ok(this.service.findOrdersWithProductDetailsByCustomerId(id));
    }
}

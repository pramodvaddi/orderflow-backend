package com.pramodvaddiraju.orderflow_backend.controller;

import com.pramodvaddiraju.orderflow_backend.dto.OrderRequestDto;
import com.pramodvaddiraju.orderflow_backend.dto.OrderResponseDto;
import com.pramodvaddiraju.orderflow_backend.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto){
        log.info("Request received to create new order for customer: {}", orderRequestDto.getCustomerName());
        return ResponseEntity.status(201).body(orderService.createOrder(orderRequestDto));
    }

    @GetMapping("/status")
    ResponseEntity<List<OrderResponseDto>>getOrderByStatus(@RequestParam String status){
        return ResponseEntity.ok().body(orderService.getOrderByStatus(status));
    }

    @GetMapping
    ResponseEntity<Page<OrderResponseDto>> getAllOrders(Pageable pageable){
        return ResponseEntity.ok().body(orderService.getAllOrders(pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok().body(orderService.getOrderById(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}

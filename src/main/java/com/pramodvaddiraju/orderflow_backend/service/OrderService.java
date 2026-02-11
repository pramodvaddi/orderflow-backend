package com.pramodvaddiraju.orderflow_backend.service;

import com.pramodvaddiraju.orderflow_backend.dto.OrderRequestDto;
import com.pramodvaddiraju.orderflow_backend.dto.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
    List<OrderResponseDto> getOrderByStatus(String status);
    Page<OrderResponseDto> getAllOrders(Pageable pageable);
    OrderResponseDto getOrderById(Long id);
    void deleteOrderById(Long id);
}

package com.pramodvaddiraju.orderflow_backend.service;

import com.pramodvaddiraju.orderflow_backend.dto.OrderRequestDto;
import com.pramodvaddiraju.orderflow_backend.dto.OrderResponseDto;
import com.pramodvaddiraju.orderflow_backend.entity.Order;
import com.pramodvaddiraju.orderflow_backend.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;
    private ModelMapper modelMapper;
    //private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);


    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
    }



    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = modelMapper.map(orderRequestDto, Order.class);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderResponseDto.class);
    }

    @Override
    public List<OrderResponseDto> getOrderByStatus(String status) {
        return List.of();
    }

    @Override
    public Page<OrderResponseDto> getAllOrders(Pageable pageable) {
        return null;
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        return null;
    }

    @Override
    public void deleteOrderById(Long id) {

    }
}

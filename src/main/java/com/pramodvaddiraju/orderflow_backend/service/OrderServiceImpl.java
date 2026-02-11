package com.pramodvaddiraju.orderflow_backend.service;

import com.pramodvaddiraju.orderflow_backend.controller.OrderController;
import com.pramodvaddiraju.orderflow_backend.dto.OrderRequestDto;
import com.pramodvaddiraju.orderflow_backend.dto.OrderResponseDto;
import com.pramodvaddiraju.orderflow_backend.entity.Order;
import com.pramodvaddiraju.orderflow_backend.exceptionhandling.ResourceNotFoundException;
import com.pramodvaddiraju.orderflow_backend.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;
    private ModelMapper modelMapper;
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);


    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
    }



    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {


        log.debug("Mapping OrderRequestDto to Order entity");
        Order order = modelMapper.map(orderRequestDto, Order.class);
        order.setStatus("CREATED");
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        log.info("Order successfully created for the customer: {}", savedOrder.getCustomerName());
        return modelMapper.map(savedOrder, OrderResponseDto.class);
    }

    @Override
    public List<OrderResponseDto> getOrderByStatus(String status) {
        List<Order> orders = orderRepository.findByStatusIgnoreCase(status);

        List<OrderResponseDto> responseList = new ArrayList<>();

        for(Order order: orders){
            OrderResponseDto dto = modelMapper.map(order, OrderResponseDto.class);
            responseList.add(dto);
        }
        return responseList;
    }

    @Override
    public Page<OrderResponseDto> getAllOrders(Pageable pageable) {

           return orderRepository.findAll(pageable)
                   .map(m -> modelMapper.map(m, OrderResponseDto.class));

    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        log.debug("Fetching order with id: ", id);
        Order order = orderRepository.findById(id).orElseThrow(
                () -> { log.warn("No order found with this id: {} ", id);
                return new ResourceNotFoundException("Order not found: "+ id);
                }
        );
        log.info("Order fetched with id: " + id);
        return modelMapper.map(order, OrderResponseDto.class);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}

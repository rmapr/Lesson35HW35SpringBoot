package com.springboot.lesson35hw35springboot.service;

import com.springboot.lesson35hw35springboot.dto.OrderDto;
import com.springboot.lesson35hw35springboot.entity.Order;

import java.util.List;


public interface OrderService {
    Order getOrderById(Integer id);
    List<Order> getAllOrders();
    Integer addOrder(OrderDto products);
    Order updateOrder(Order order);
    void deleteOrder(Integer id);


}

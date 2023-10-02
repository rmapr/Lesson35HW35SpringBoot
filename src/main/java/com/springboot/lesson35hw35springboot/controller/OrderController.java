package com.springboot.lesson35hw35springboot.controller;

import com.springboot.lesson35hw35springboot.dto.OrderDto;
import com.springboot.lesson35hw35springboot.entity.Order;
import com.springboot.lesson35hw35springboot.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger("logger");

    @GetMapping("/{id}")
    public Order getOrderByIdController(@PathVariable("id") Integer id) {
        logger.info("OrderController - call method get from Order by Id...");
        return orderService.getOrderById(id);
    }

    @GetMapping("/all")
    public List<Order> getAllOrdersController() {
        logger.info("OrderController - call method get all Orders...");
        return orderService.getAllOrders();
    }

    @PostMapping("")
    public Integer addOrderController(@RequestBody OrderDto orderDto) {
        logger.info("OrderController - call method add order...");
//        products.forEach(product -> log.info("Add product: " + product.getName() + " " + product.getCost()));
        return orderService.addOrder(orderDto);
    }
    @PutMapping("/update")
    public Order updateOrderController(@RequestBody Order order) {
        logger.info("OrderController - call method update order...");
        return orderService.updateOrder(order);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteOrderController(@PathVariable("id") final Integer id) {
        logger.info("OrderController - call method delete from Order by Id...");
        logger.info("Delete order: " + id);
        orderService.deleteOrder(id);
    }

}

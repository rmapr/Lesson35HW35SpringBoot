package com.springboot.lesson35hw35springboot.service.impl;

import com.springboot.lesson35hw35springboot.dto.OrderDto;
import com.springboot.lesson35hw35springboot.entity.Order;
import com.springboot.lesson35hw35springboot.entity.Product;
import com.springboot.lesson35hw35springboot.exception.OrderNotFoundException;
import com.springboot.lesson35hw35springboot.exception.ProductNotFoundException;
import com.springboot.lesson35hw35springboot.repository.OrderRepository;
import com.springboot.lesson35hw35springboot.repository.ProductRepository;
import com.springboot.lesson35hw35springboot.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger("logger");

    @Override
    public Order getOrderById(Integer id) {
        logger.info("OrderServiceImpl - Отримання замовлення з індексом {}: ", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("OrderServiceImpl - Замовлення з індексом {} немає", id);
                            return new OrderNotFoundException("ORDER_NOT_FOUND");
                        }
                );
        order.setCost(calcOrderCost(order.getProducts()));
        logger.info("OrderServiceImpl - Замовлення з індексом {} присутнє", id);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        logger.info("OrderServiceImpl - Визов методу показати всі замовлення ...: ");
        return orderRepository.findAll();
    }

    @Override
    public Integer addOrder(OrderDto orderDto) {
        logger.info("OrderServiceImpl - Визов методу додавання замовлення ...: ");
        List<Product> products = getProductsList(orderDto.getProducts());
        Order order = new Order()
                .setDate(new Timestamp(System.currentTimeMillis()))
                .setName(orderDto.getName())
                .setProducts(products);
        order.setCost(calcOrderCost(products));
        return orderRepository.save(order).getId();
    }

    @Override
    public Order updateOrder(Order order) {
        logger.info("OrderServiceImpl - Визов методу внесення змін до замовлення ...: ");
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        logger.info("OrderServiceImpl - Визов методу видалення замовлення ...: ");
        if (!orderRepository.existsById(id)) {
            logger.error("OrderServiceImpl - Видалити неможливо. Замовлення з індексом {} відсутнє", id);
            throw new OrderNotFoundException("ORDER_NOT_FOUND");
        }
        orderRepository.deleteById(id);
        logger.info("OrderServiceImpl - Замовлення {} видалено", id);
    }

    private Double calcOrderCost(List<Product> products) {
        return products.stream().mapToDouble(Product::getCost).sum();
    }

    private List<Product> getProductsList(List<Integer> listProducts) {
        logger.info("OrderServiceImpl - Отримали Продукти за списком індексів {}...", listProducts);
        List<Product> products = productRepository.findAllById(listProducts);
        List<Integer> getAllProductList = products.stream().map(Product::getId).toList();
        listProducts.removeAll(getAllProductList);
        if (!listProducts.isEmpty()) {
            logger.error("OrderServiceImpl - Продуктів з індексами {} в таблиці не має", listProducts);
            throw new ProductNotFoundException("Could not found products with id: " + listProducts);
        }
        return products;
    }
}

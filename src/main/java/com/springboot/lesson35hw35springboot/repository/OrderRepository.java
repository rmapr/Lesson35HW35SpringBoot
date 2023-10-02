package com.springboot.lesson35hw35springboot.repository;

import com.springboot.lesson35hw35springboot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}

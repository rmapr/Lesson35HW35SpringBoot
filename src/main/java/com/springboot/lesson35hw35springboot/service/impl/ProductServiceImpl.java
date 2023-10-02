package com.springboot.lesson35hw35springboot.service.impl;

import com.springboot.lesson35hw35springboot.dto.ProductDto;
import com.springboot.lesson35hw35springboot.entity.Product;
import com.springboot.lesson35hw35springboot.exception.OrderNotFoundException;
import com.springboot.lesson35hw35springboot.exception.ProductNotFoundException;
import com.springboot.lesson35hw35springboot.repository.ProductRepository;
import com.springboot.lesson35hw35springboot.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger("logger");

    @Override
    public List<Product> getProducts() {
        logger.info("ProductServiceImpl - Визов методу показати всі Продукти ...: ");
        return productRepository.findAll();
    }

    @Override
    public Integer addProduct(ProductDto productDto) {
        logger.info("ProductServiceImpl - Визов методу додати Продукт ...: ");
        Product product = new Product()
                .setName(productDto.getName())
                .setCost(productDto.getCost());
        return productRepository.save(product).getId();
    }

    @Override
    public Product getProductById(Integer id) {
        logger.info("ProductServiceImpl - Отримання Продукту за індексом {}: ", id);
        return productRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("ProductServiceImpl - Продукт з індексом {} відсутній", id);
                            return new ProductNotFoundException("PRODUCT NOT FOUND");
                        }
                );
    }

    @Override
    public Product updateProduct(Product product) {
        logger.info("ProductServiceImpl - Визов методу внесення змін до Продукту ...: ");
        return productRepository.save(product);
    }
}

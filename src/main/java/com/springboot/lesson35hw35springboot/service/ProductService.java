package com.springboot.lesson35hw35springboot.service;

import com.springboot.lesson35hw35springboot.dto.ProductDto ;
import com.springboot.lesson35hw35springboot.entity.Product ;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Integer addProduct(ProductDto product);

    Product getProductById(Integer id);

    Product updateProduct(Product product);

}

package com.github.example.app.service;

import com.github.example.app.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<Product> getAllProducts(int page, int size, String sortBy, String sortDir);

    Page<Product> getProductsByCategory(String category, int page, int size);
}

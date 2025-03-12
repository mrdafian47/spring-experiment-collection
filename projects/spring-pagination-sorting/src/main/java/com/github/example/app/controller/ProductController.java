package com.github.example.app.controller;

import com.github.example.app.dto.PaginatedResponse;
import com.github.example.app.entity.Product;
import com.github.example.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public PaginatedResponse<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<Product> productPage = productService.getAllProducts(page, size, sortBy, sortDir);
        return new PaginatedResponse<>(productPage);
    }

    @GetMapping("/products/category")
    public PaginatedResponse<Product> getProductsByCategory(
            @RequestParam String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Product> productPage = productService.getProductsByCategory(category, page, size);
        return new PaginatedResponse<>(productPage);
    }
}

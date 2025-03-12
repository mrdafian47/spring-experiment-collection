package com.github.example.app.repository;

import com.github.example.app.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p.name, p.price FROM Product p WHERE p.category = :category")
    Page<Object[]> findProductNamesAndPricesByCategory(@Param("category") String category, Pageable pageable);

    Page<Product> findByCategory(String category, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price > :price ORDER BY p.price ASC")
    List<Product> findProductsByPriceGreaterThan(@Param("price") Double price, Pageable pageable);
}

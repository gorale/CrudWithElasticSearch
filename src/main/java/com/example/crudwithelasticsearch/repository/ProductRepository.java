package com.example.crudwithelasticsearch.repository;

import com.example.crudwithelasticsearch.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Override
    Optional<Product> findById(Long id);

    @Override
    Page<Product> findAll(Pageable pageable);
}

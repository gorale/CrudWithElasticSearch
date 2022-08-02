package com.example.crudwithelasticsearch.jpaRepository;


import com.example.crudwithelasticsearch.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product,Long> {

}

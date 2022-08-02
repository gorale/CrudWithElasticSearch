package com.example.crudwithelasticsearch.repository;

import com.example.crudwithelasticsearch.model.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {

    List<Product> findByName(String name);


}

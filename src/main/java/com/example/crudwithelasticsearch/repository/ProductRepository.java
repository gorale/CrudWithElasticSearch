package com.example.crudwithelasticsearch.repository;

import com.example.crudwithelasticsearch.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

//    Page<Product> findByName(String name, Pageable pageable);

//    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
//    Page<Product> findByNameUsingCustomQuery(String name, Pageable pageable);

    List<Product> findByName(String name);


}

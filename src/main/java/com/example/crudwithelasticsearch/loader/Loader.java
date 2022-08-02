package com.example.crudwithelasticsearch.loader;

import com.example.crudwithelasticsearch.model.entity.Product;
import com.example.crudwithelasticsearch.jpaRepository.ProductJpaRepository;
import com.example.crudwithelasticsearch.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class Loader {
//
//    @Autowired
//    ElasticsearchOperations operations;
//
//    @Autowired
//    ProductRepository productRepository;
//
//    @Autowired
//    ProductJpaRepository productJpaRepository;
//
////    @PostConstruct
//    @Transactional
////    @Scheduled(cron = "0 */1 * * * *")
//    public void loadAll(){
//
//        List<Product> products = productJpaRepository.findAll();
//        productRepository.saveAll(products);
//
//    }

}

package com.example.crudwithelasticsearch.service;

import com.example.crudwithelasticsearch.model.dto.ProductDto;
import com.example.crudwithelasticsearch.model.entity.Product;
import com.example.crudwithelasticsearch.model.mapper.ProductMapper;
import com.example.crudwithelasticsearch.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductDto createProduct(ProductDto productDto){


        Product product = this.productMapper.convertToEntity(productDto);

        Product savedProduct = this.productRepository.save(product);

        return this.productMapper.convertToDto(savedProduct);

    }

    public Optional<ProductDto> getProduct(Long id){
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(productMapper.convertToDto(product.get()));
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Optional<ProductDto> updateProduct(ProductDto productDto, Long id){

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return Optional.empty();
        }
        Product product = productMapper.convertToEntity(productDto);

        productOptional.get().setName(product.getName());
        productOptional.get().setCount(product.getCount());
        productOptional.get().setPrice(product.getPrice());

        Product savedProduct = productRepository.save(productOptional.get());
        return Optional.of(productMapper.convertToDto(savedProduct));
    }

    public Page<Product> getAll(Pageable pageable) {

        return productRepository.findAll(pageable);
    }


}

package com.example.crudwithelasticsearch.service;

import com.example.crudwithelasticsearch.model.dto.ProductDto;
import com.example.crudwithelasticsearch.model.entity.Product;
import com.example.crudwithelasticsearch.model.mapper.ProductMapper;
import com.example.crudwithelasticsearch.jpaRepository.ProductJpaRepository;
import com.example.crudwithelasticsearch.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    private ProductJpaRepository productJpaRepository;

    private final ProductMapper productMapper;

    public Optional<Product> createProduct(ProductDto productDto){

        Product product = Product.builder()
                .name(productDto.getName())
                .count(productDto.getCount())
                .price(productDto.getPrice())
                .build();

        productJpaRepository.save(product);
        productRepository.save(product);

        return Optional.of(product);

    }

    public Optional<ProductDto> getProduct(Long id){
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(productMapper.convertToDto(product.get()));
    }

    public void deleteProduct(Long id){
        productJpaRepository.deleteById(id);
        productRepository.deleteById(id);
    }

    public Optional<ProductDto> updateProduct(ProductDto productDto, Long id){

        Optional<Product> productOptional = productJpaRepository.findById(id);
        Optional<Product> productOptionalES = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return Optional.empty();
        }
        Product product = productMapper.convertToEntity(productDto);

        productOptional.get().setName(product.getName());
        productOptional.get().setCount(product.getCount());
        productOptional.get().setPrice(product.getPrice());

        productOptionalES.get().setName(product.getName());
        productOptionalES.get().setCount(product.getCount());
        productOptionalES.get().setPrice(product.getPrice());

        Product savedProduct = productJpaRepository.save(productOptional.get());
        productRepository.save(productOptionalES.get());
        return Optional.of(productMapper.convertToDto(savedProduct));
    }

    public Page<Product> getAll(Pageable pageable) {

        return productRepository.findAll(pageable);
    }



}

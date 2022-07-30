package com.example.crudwithelasticsearch.model.mapper;


import com.example.crudwithelasticsearch.model.dto.ProductDto;
import com.example.crudwithelasticsearch.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public Product convertToEntity(ProductDto dto) {
        Product product=new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setCount(dto.getCount());
        product.setPrice(dto.getPrice());
        return product;
    }


    public ProductDto convertToDto(Product entity) {
        return ProductDto.builder().
                id(entity.getId()).
                name(entity.getName()).
                count(entity.getCount()).
                price(entity.getPrice()).build();
    }

}

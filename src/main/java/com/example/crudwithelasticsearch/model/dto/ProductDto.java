package com.example.crudwithelasticsearch.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;


    private String name;


    private int count;


    private double price;

    @Override
    public String toString() {
        return "Product " +
                "id = " + id +
                ", product name = " + name +
                ", product count = " + count +
                ", product price = " + price;
    }
}

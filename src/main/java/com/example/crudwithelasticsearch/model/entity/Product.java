package com.example.crudwithelasticsearch.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.annotation.processing.Generated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "product")
public class Product {

    @Id
    private String id;

    private String name;

    private Double price;

    private Integer count;




}

package com.example.crudwithelasticsearch.controller;

import com.example.crudwithelasticsearch.model.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    ObjectMapper mapper;
    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void createProductSuccessTest() throws Exception {


        ProductDto product = new ProductDto();
        product.setId(1L);
        product.setName("apple");
        product.setPrice(60000.0);
        product.setCount(27);


        String userJson = mapper.writeValueAsString(product);
        mockMvc.perform(MockMvcRequestBuilders.post("/crud_with_elasticsearch/product")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(userJson.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/crud_with_elasticsearch/product/{id}", "ab1e0330-0910-4d58-983b-186a68bdf6ba")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getAllProductsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/crud_with_elasticsearch/product/products")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void updateProductSuccessTest() throws Exception {

        ProductDto product = new ProductDto();
        product.setId(1L);
        product.setName("apple");
        product.setPrice(60000.0);
        product.setCount(27);


        String userJson = mapper.writeValueAsString(product);

        mockMvc.perform(MockMvcRequestBuilders.put("/crud_with_elasticsearch/product/update/{id}", "ab1e0330-0910-4d58-983b-186a68bdf6ba")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(userJson.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}

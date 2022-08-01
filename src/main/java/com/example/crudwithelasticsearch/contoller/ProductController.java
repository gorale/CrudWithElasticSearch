package com.example.crudwithelasticsearch.contoller;


import com.example.crudwithelasticsearch.model.dto.ProductDto;
import com.example.crudwithelasticsearch.model.entity.Product;
import com.example.crudwithelasticsearch.repository.ProductRepository;
import com.example.crudwithelasticsearch.response.EntityCreatingResponse;
import com.example.crudwithelasticsearch.response.EntityDeletingResponse;
import com.example.crudwithelasticsearch.response.EntityLookupResponse;
import com.example.crudwithelasticsearch.response.EntityUpdatingResponse;
import com.example.crudwithelasticsearch.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("crud_with_elasticsearch/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);



    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        logger.info("Received a request to create a Product.");
        Optional<Product> optionalProduct = productService.createProduct(productDto);
        if(optionalProduct.isEmpty()){
            return new EntityCreatingResponse<Product>().onFailure("Can not create product");
        }
        logger.info("Product is created.");
        return new EntityCreatingResponse<ProductDto>().onSuccessES(productDto.toString());
    }


    @GetMapping(value = "/search/{name}")
    public List<Product> findAll(@PathVariable String name)
    {
        return productRepository.findByName(name);
    }




    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") String id) {
        logger.info("Received a request to get a Product with id " + id);
        Optional<ProductDto> productDto = productService.getProduct(id);

        if (productDto.isPresent()) {
            logger.info("Product with given id found");
            return new EntityLookupResponse<ProductDto>().onSuccess(productDto.get());
        }
        logger.warn("There is not product with given id");
        return new EntityLookupResponse<ProductDto>().onFailure("Product not found");
    }
//
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") String id) {
        logger.info("Received a request to delete a Product with id " + id);
        Optional<ProductDto> productDto = productService.getProduct(id);

        if (productDto.isPresent()) {
            productService.deleteProduct(id);
            return new EntityDeletingResponse<ProductDto>()
                    .onSuccess(productDto.get(), "Product");
        }
        logger.warn("There is not product with given id");
        return new EntityLookupResponse<ProductDto>().onFailure("Can not delete product");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto,
                                            @PathVariable("id") String id) {
        logger.info("Received a request to update a product with id " + id);
        Optional<ProductDto> optionalProductDto = productService.updateProduct(productDto, id);

        if (optionalProductDto.isEmpty()) {
            logger.warn("There is not a product with this id.");
            return new EntityLookupResponse<Product>().onFailure("Can't update this product");
        }
        logger.info("Product successfully updated.");
        return new EntityUpdatingResponse<ProductDto>().onSuccess(optionalProductDto.get());
    }

    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            List<Product> products = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);

            Page<Product> pageProducts;
            pageProducts = productService.getAll(paging);

            products = pageProducts.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("products", products);
            response.put("currentPage", pageProducts.getNumber());
            response.put("totalItems", pageProducts.getTotalElements());
            response.put("totalPages", pageProducts.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

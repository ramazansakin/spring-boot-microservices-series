package com.sivalabs.catalogservice.web.controllers;

import com.sivalabs.catalogservice.entities.Product;
import com.sivalabs.catalogservice.exceptions.ProductNotFoundException;
import com.sivalabs.catalogservice.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> allProducts(HttpServletRequest request) {
        log.info("Finding all products");
        log.info("AUTH_HEADER: " + request.getHeader("AUTH_HEADER"));
        return productService.findAllProducts();
    }

    @GetMapping("/{code}")
    public Product productByCode(@PathVariable String code) {
        log.info("Finding product by code :" + code);
        return productService.findProductByCode(code)
                .orElseThrow(() -> new ProductNotFoundException("Product with code [" + code + "] doesn't exist"));
    }

    @PostMapping("")
    public Product create(@RequestBody @Valid Product product) {
        return productService.createProduct(product);
    }

    @PutMapping
    public Product update(@PathVariable Long id, @RequestBody @Valid Product product) {
        if (!productService.findById(id).isPresent())
            throw new ProductNotFoundException("Product not found with id : " + id);
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("")
    public Boolean deleteProduct(@PathVariable Long id) {
        if (!productService.findById(id).isPresent())
            throw new ProductNotFoundException("Product not found with id : " + id);
        return productService.deleteProduct(id);
    }

}

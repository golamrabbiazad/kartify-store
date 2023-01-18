package com.golamrabbiazad.primaxstorebackend.controller;

import com.golamrabbiazad.primaxstorebackend.exception.ProductNotFoundException;
import com.golamrabbiazad.primaxstorebackend.model.Product;
import com.golamrabbiazad.primaxstorebackend.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) throws ProductNotFoundException {
        return productService.getProduct(id);
    }

    @GetMapping(value = "/search")
    public List<Product> searchProductByName(@RequestParam String name) {
        return productService.searchProductByName(name);
    }

    @GetMapping(value = "/sort_by")
    public List<Product> sortProductByPrice(@RequestParam String price) {
        return productService.sortByPrice(price);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody @Valid Product product) {
        productService.createProduct(product);

        return product;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}

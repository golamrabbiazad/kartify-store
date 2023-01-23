package com.golamrabbiazad.primaxstorebackend.service;

import com.golamrabbiazad.primaxstorebackend.exception.ProductNotFoundException;
import com.golamrabbiazad.primaxstorebackend.model.entity.Product;
import com.golamrabbiazad.primaxstorebackend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<String> getAllProductIds() {
        return productRepository.findAll().stream().map(Product::getProductId).collect(Collectors.toList());
    }

    public List<Product> getProductsByIds(List<String> productIds) {
        return productRepository.findAllById(productIds);
    }

    public Product getProduct(String id) throws ProductNotFoundException {
        var product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + " not found.");
        }

        return product.get();
    }

    public List<Product> searchProductByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    public List<Product> sortByPrice(String listOrder) {
        var products = productRepository.findAll();
        if (listOrder.equals("asc")) {
            products.sort(Comparator.comparingDouble(Product::getPrice));
        }

        if (listOrder.equals("desc")) {
            products.sort(Comparator.comparingDouble(Product::getPrice).reversed());
        }

        return products;
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public Product updateProduct(String id, Product product) throws ProductNotFoundException {
        // find the product by id
        var optionalOldProduct = productRepository.findById(id);
        if (optionalOldProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + " not found.");
        }

        // Product Object here.
        var newProduct = optionalOldProduct.get();
        newProduct.setProductId(product.getProductId());
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setImage(product.getImage());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setOrders(product.getOrders());
        newProduct.setCreatedAt(product.getCreatedAt());
        newProduct.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(newProduct);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

}

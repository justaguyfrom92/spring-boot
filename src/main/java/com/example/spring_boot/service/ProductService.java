package com.example.spring_boot.service;

import com.example.spring_boot.model.Product;
import com.example.spring_boot.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService
{
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product)
    {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    // Get product by ID
    public Optional<Product> getProductById(Long id)
    {
        return Optional.ofNullable(productRepository.findById(id))
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));
    }

    public List<Product> getProductsByCategory(String category)
    {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    public List<String> getAllCategories()
    {
        return productRepository.findAll().stream()
                .map(Product::getCategory)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }
    
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

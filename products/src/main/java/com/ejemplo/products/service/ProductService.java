package com.ejemplo.products.service;

import com.ejemplo.products.model.Product;
import com.ejemplo.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    public Product updateProduct(Long id, Product updated) {
        return repository.findById(id)
                .map(product -> {
                    product.setName(updated.getName());
                    product.setDescription(updated.getDescription());
                    product.setPrice(updated.getPrice());
                    product.setCategory(updated.getCategory());
                    return repository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return repository.findAll(pageable);
    }
}

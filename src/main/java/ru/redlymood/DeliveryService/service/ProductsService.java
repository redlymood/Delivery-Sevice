package ru.redlymood.DeliveryService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.redlymood.DeliveryService.model.Product;
import ru.redlymood.DeliveryService.repository.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductsService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }
    public Product findOne(int id) {
        Optional<Product> foundProduct = productsRepository.findById(id);
        return foundProduct.orElse(null);
    }
    @Transactional
    public void save(Product product) {
        productsRepository.save(product);
    }
    @Transactional
    public void update(int id, Product updateProduct) {
        updateProduct.setId(id);
        productsRepository.save(updateProduct);
    }
    @Transactional
    public void delete(int id) {
        productsRepository.deleteById(id);
    }
}


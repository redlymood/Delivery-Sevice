package ru.redlymood.DeliveryService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.redlymood.DeliveryService.model.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
}

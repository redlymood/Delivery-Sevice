package ru.redlymood.DeliveryService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.redlymood.DeliveryService.model.Customer;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer> {
}
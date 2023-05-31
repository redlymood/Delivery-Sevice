package ru.redlymood.DeliveryService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.redlymood.DeliveryService.model.Delivery;

@Repository
public interface DeliveriesRepository extends JpaRepository<Delivery, Integer> {
}

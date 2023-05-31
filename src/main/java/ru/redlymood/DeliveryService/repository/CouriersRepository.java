package ru.redlymood.DeliveryService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.redlymood.DeliveryService.model.Courier;

@Repository
public interface CouriersRepository extends JpaRepository<Courier, Integer> {
}

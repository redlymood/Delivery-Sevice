package ru.redlymood.DeliveryService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.redlymood.DeliveryService.model.Delivery;
import ru.redlymood.DeliveryService.repository.DeliveriesRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DeliveriesService {
    private final DeliveriesRepository deliveriesRepository;
    @Autowired
    public DeliveriesService(DeliveriesRepository deliveriesRepository) {
        this.deliveriesRepository = deliveriesRepository;
    }
    public List<Delivery> findAll() {
        return deliveriesRepository.findAll();
    }
    public Delivery findOne(int id) {
        Optional<Delivery> foundDelivery = deliveriesRepository.findById(id);
        return foundDelivery.orElse(null);
    }
    @Transactional
    public void save(Delivery delivery) {
        deliveriesRepository.save(delivery);
    }
    @Transactional
    public void update(int id, Delivery updateDelivery) {
        updateDelivery.setId(id);
        deliveriesRepository.save(updateDelivery);
    }
    @Transactional
    public void delete(int id) {
        deliveriesRepository.deleteById(id);
    }
}


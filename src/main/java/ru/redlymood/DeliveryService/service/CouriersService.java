package ru.redlymood.DeliveryService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.redlymood.DeliveryService.model.Courier;
import ru.redlymood.DeliveryService.repository.CouriersRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CouriersService {
    private final CouriersRepository couriersRepository;
    @Autowired
    public CouriersService(CouriersRepository couriersRepository) {
        this.couriersRepository = couriersRepository;
    }

    public List<Courier> findAll() {
        return couriersRepository.findAll();
    }

    public Courier findOne(int id) {
        Optional<Courier> foundCourier = couriersRepository.findById(id);
        return foundCourier.orElse(null);
    }
    @Transactional
    public void save(Courier courier) {
        couriersRepository.save(courier);
    }

    @Transactional
    public void update(int id, Courier updateCourier) {
        updateCourier.setId(id);
        couriersRepository.save(updateCourier);
    }
    @Transactional
    public void delete(int id) {
        couriersRepository.deleteById(id);
    }
}
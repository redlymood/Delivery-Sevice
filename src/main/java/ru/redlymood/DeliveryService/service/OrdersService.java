package ru.redlymood.DeliveryService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.redlymood.DeliveryService.model.Order;
import ru.redlymood.DeliveryService.model.Product;
import ru.redlymood.DeliveryService.repository.OrdersRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final OrdersRepository ordersRepository;
    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Order> findAll() {
        return ordersRepository.findAll();
    }
    public Order findOne(int id) {
        Optional<Order> foundOrder = ordersRepository.findById(id);
        return foundOrder.orElse(null);
    }
    @Transactional
    public void save(Order order) {
        ordersRepository.save(order);
    }
    @Transactional
    public void update(int id, Order updateOrder) {
        updateOrder.setId(id);
        ordersRepository.save(updateOrder);
    }
    @Transactional
    public void delete(int id) {
        ordersRepository.deleteById(id);
    }

    @Transactional
    public void assign(int id, List<Product> selectedProducts) {
        ordersRepository.findById(id).ifPresent(order -> {order.setProducts(selectedProducts);});
    }
}


package ru.redlymood.DeliveryService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.redlymood.DeliveryService.model.Customer;
import ru.redlymood.DeliveryService.repository.CustomersRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomersService {
    private final CustomersRepository customersRepository;
    @Autowired
    public CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }
    public List<Customer> findAll() {
        return customersRepository.findAll();
    }
    public Customer findOne(int id) {
        Optional<Customer> foundCustomer = customersRepository.findById(id);
        return foundCustomer.orElse(null);
    }
    @Transactional
    public void save(Customer customer) {
        customersRepository.save(customer);
    }
    @Transactional
    public void update(int id, Customer updateCustomer) {
        updateCustomer.setId(id);
        customersRepository.save(updateCustomer);
    }
    @Transactional
    public void delete(int id) {
        customersRepository.deleteById(id);
    }
}


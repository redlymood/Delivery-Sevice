package ru.redlymood.DeliveryService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.redlymood.DeliveryService.model.Customer;
import ru.redlymood.DeliveryService.model.Delivery;
import ru.redlymood.DeliveryService.service.CustomersService;

import javax.validation.Valid;

@Controller
@RequestMapping("/customers")
public class CustomersController {
    private final CustomersService customersService;

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping("/new")
    public String newCustomer(@ModelAttribute("customer")Customer customer) {
        return "customers/new.html";
    }
    @PostMapping
    public String create(@ModelAttribute("customer") @Valid Customer customer,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "customers/new.html";
        customersService.save(customer);
        return "redirect:/orders";
    }
}

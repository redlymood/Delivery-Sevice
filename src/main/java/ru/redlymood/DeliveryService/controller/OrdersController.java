package ru.redlymood.DeliveryService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.redlymood.DeliveryService.model.Delivery;
import ru.redlymood.DeliveryService.model.Order;
import ru.redlymood.DeliveryService.model.Product;
import ru.redlymood.DeliveryService.service.CustomersService;
import ru.redlymood.DeliveryService.service.OrdersService;
import ru.redlymood.DeliveryService.service.ProductsService;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final ProductsService productsService;
    private final CustomersService customersService;
    @Autowired
    public OrdersController(OrdersService ordersService, ProductsService productsService, CustomersService customersService) {
        this.productsService = productsService;
        this.ordersService = ordersService;
        this.customersService = customersService;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("orders", ordersService.findAll());
        return "orders/index.html";
    }

    @GetMapping("/new")
    public String newOrder(Model model, @ModelAttribute("order") Order order) {
        model.addAttribute("customers", customersService.findAll());
        model.addAttribute("productList", productsService.findAll());
        return "orders/new.html";
    }
    @PostMapping()
    public String create(@ModelAttribute("order") Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "orders/new.html";
        }
        ordersService.save(order);
        return "orders/show.html";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("order") Order order) {
        model.addAttribute("customers", customersService.findAll());
        model.addAttribute("productList", productsService.findAll());
        model.addAttribute("order", ordersService.findOne(id));
        return "/orders/show.html";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("order") Order order,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "orders/edit.html";
        ordersService.update(id, order);
        return "orders/show.html";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ordersService.delete(id);
        return "orders/show.html";
    }
}


package ru.redlymood.DeliveryService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.redlymood.DeliveryService.model.Delivery;
import ru.redlymood.DeliveryService.service.CouriersService;
import ru.redlymood.DeliveryService.service.DeliveriesService;
import ru.redlymood.DeliveryService.service.OrdersService;

@Controller
@RequestMapping("/deliveries")
public class DeliveriesController {
    private final DeliveriesService deliveriesService;
    private final CouriersService couriersService;
    private final OrdersService ordersService;
    @Autowired
    public DeliveriesController(DeliveriesService deliveriesService, CouriersService couriersService, OrdersService ordersService) {
        this.deliveriesService = deliveriesService;
        this.couriersService = couriersService;
        this.ordersService = ordersService;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("deliveries", deliveriesService.findAll());
        return "deliveries/index.html";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("delivery") Delivery delivery) {
        model.addAttribute("delivery", deliveriesService.findOne(id));
        return "deliveries/show.html";
    }
    @GetMapping("/new")
    public String newDelivery(Model model, @ModelAttribute("delivery") Delivery delivery) {
        model.addAttribute("couriers", couriersService.findAll());
        model.addAttribute("orders", ordersService.findAll());
        return "deliveries/new.html";
    }
    @PostMapping
    public String create(@ModelAttribute("delivery") Delivery delivery,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "deliveries/new.html";
        deliveriesService.save(delivery);
        return "redirect:/deliveries";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("delivery", deliveriesService.findOne(id));
        model.addAttribute("couriers", couriersService.findAll());
        model.addAttribute("orders", ordersService.findAll());
        return "deliveries/edit.html";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("delivery") Delivery delivery,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "deliveries/edit.html";
        deliveriesService.update(id, delivery);
        return "redirect:/deliveries";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        deliveriesService.delete(id);
        return "redirect:/deliveries";
    }
}


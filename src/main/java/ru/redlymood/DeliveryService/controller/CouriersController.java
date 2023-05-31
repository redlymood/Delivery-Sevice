package ru.redlymood.DeliveryService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.redlymood.DeliveryService.model.Courier;
import ru.redlymood.DeliveryService.service.CouriersService;

import javax.validation.Valid;

@Controller
@RequestMapping("/couriers")
public class CouriersController {
    private final CouriersService couriersService;

    @Autowired
    public CouriersController(CouriersService couriersService) {
        this.couriersService = couriersService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("couriers", couriersService.findAll());
        return "couriers/index.html";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("courier") Courier courier) {
        model.addAttribute("courier", couriersService.findOne(id));
        return "couriers/show.html";
    }

    @GetMapping("/new")
    public String newCourier(@ModelAttribute("courier") Courier courier) {
        return "couriers/new.html";
    }

    @PostMapping
    public String create(@ModelAttribute("courier") @Valid Courier courier,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "couriers/new.html";
        couriersService.save(courier);
        return "redirect:/couriers";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("courier", couriersService.findOne(id));
        return "couriers/edit.html";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("courier") Courier courier,
                         @PathVariable("id") int id,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "couriers/edit";
        }
        couriersService.update(id, courier);
        return "redirect:/couriers";
    }
    @DeleteMapping ("/{id}")
    public String delete(@PathVariable("id") int id) {
        couriersService.delete(id);
        return "redirect:/couriers";
    }
}


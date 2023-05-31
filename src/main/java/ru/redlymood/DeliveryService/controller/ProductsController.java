package ru.redlymood.DeliveryService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.redlymood.DeliveryService.model.Product;
import ru.redlymood.DeliveryService.service.ProductsService;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;
    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("products", productsService.findAll());
        return "products/index.html";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("product") Product product) {
        model.addAttribute("product", productsService.findOne(id));
        return "products/show.html";
    }
    @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product) {
        return "products/new.html";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "products/new.html";
        productsService.save(product);
        return "redirect:/products";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", productsService.findOne(id));
        return "products/edit.html";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") Product product,
                         @PathVariable("id") int id,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "products/edit";
        }
        productsService.update(id, product);
        return "redirect:/products";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productsService.delete(id);
        return "redirect:/products";
    }
}


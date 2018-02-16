package com.indiraactive.stockupdaterserver.controller;

import com.indiraactive.stockupdaterserver.dal.SupplierRepository;
import com.indiraactive.stockupdaterserver.model.Supplier;
import com.indiraactive.stockupdaterserver.model.User;
import com.indiraactive.stockupdaterserver.service.InventoryUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// TODO: Clean up this controller and create new classes to compartmentalize responsibilities.

@Controller
public class HomeController {

    @Autowired
    private InventoryUpdater inventoryUpdater;
    @Autowired
    private SupplierRepository supplierRepository;

    public HomeController(InventoryUpdater inventoryUpdater) {
        this.inventoryUpdater = inventoryUpdater;
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("user", new User()); // TODO: Get rid of user

        return "index";
    }

    @GetMapping("/manageSuppliers")
    public String manageSuppliers(Model model) {
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "manageSuppliers";
    }

    @GetMapping("/inventoryUpdater")
    public String inventoryUpdater(Model model) {
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "inventoryUpdater";
    }

    @PostMapping("/updateInventory")
    public String updateInventory(@ModelAttribute Supplier supplier) {
        inventoryUpdater.updateInventory(supplier.getSupplier_id());
        return "success";
    }

    @PostMapping("/addSupplier")
    public String addSupplier(@ModelAttribute Supplier supplier) {
        supplierRepository.save(supplier);
        return "success";
    }

    @DeleteMapping("/deleteSupplier/{supplierId}")
    public String deleteSupplier(@PathVariable int supplierId) {
        supplierRepository.delete(supplierId);
        return "success";
    }
}
package com.indiraactive.stockupdaterserver.controller;

import com.indiraactive.stockupdaterserver.model.User;
import com.indiraactive.stockupdaterserver.service.InventoryUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private InventoryUpdater inventoryUpdater;

    public HomeController(InventoryUpdater inventoryUpdater) {
        this.inventoryUpdater = inventoryUpdater;
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/updateInventory")
    public String updateInventory() {
        inventoryUpdater.updateInventory();
        return "success";
    }

}
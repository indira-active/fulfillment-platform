package com.indiraactive.fulfillmentplatform.controller;

import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import com.indiraactive.fulfillmentplatform.dao.supplier.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SupplierController {
    /**
     * Abstraction to physical database that creates, reads, and deletes suppliers
     */
    @Autowired
    private SupplierRepository supplierRepository;

    /**
     * User can manage the suppliers that the fulfillment platform uses
     * @param model Automatically passed when this endpoint is called via get request, used for building the view
     * @return View that lets a user add or delete suppliers
     */
    @GetMapping("/manageSuppliers")
    public String manageSuppliers(Model model) {
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "manageSuppliers";
    }

    /**
     * Adds a new supplier to the fulfillment platform system.
     * @param supplier Supplier to add to the fulfillment platform system
     * @return A success screen if there are no errors thrown during the process of running the screen
     */
    @PostMapping("/addSupplier")
    public String addSupplier(@ModelAttribute Supplier supplier) {
        supplierRepository.save(supplier);
        return "success";
    }

    /**
     * Deletes a given supplier by their supplierId
     * @param supplierId Id of a supplier to be deleted
     * @return A success screen if there are no errors thrown during the process of running the screen
     */
    @DeleteMapping("/deleteSupplier/{supplierId}")
    public String deleteSupplier(@PathVariable int supplierId) {
        supplierRepository.delete(supplierId);
        return "success";
    }
}

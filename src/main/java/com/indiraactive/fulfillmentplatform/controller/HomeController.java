package com.indiraactive.fulfillmentplatform.controller;

import com.indiraactive.fulfillmentplatform.dal.SupplierRepository;
import com.indiraactive.fulfillmentplatform.model.Supplier;
import com.indiraactive.fulfillmentplatform.service.InventoryUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// TODO: Clean up this controller and create new classes to compartmentalize responsibilities.

/**
 * Resolves which view the user wants for the fulfillment platform and delegates actions requested
 * by the user.
 *
 * Shows the user the home, inventory updater, and manage suppliers page.
 *
 * Handles requests to run the invenotry updater script (sync_inventory) and handles creating, reading, and deleting suppliers.
 */
@Controller
public class HomeController {

    /**
     * Runs the sync_inventory script based on a supplier provided to it
     */
    @Autowired
    private InventoryUpdater inventoryUpdater;

    /**
     * Abstraction to physical database that creates, reads, and deletes suppliers
     */
    @Autowired
    private SupplierRepository supplierRepository;

    /**
     * Provides the consumers with the home page of the fulfillment platform web app
     * @return Index page that links to other parts of the fulfillment platform
     */
    @RequestMapping("/")
    public String home() {
        return "index";
    }

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
     * Lets a user choose a supplier and update inventory based on what that selected supplier has
     * @param model Helps populate the view, automatically passed in
     * @return View that lets a user pick a supplier and run the sync_inventory script with parameters from the supplier chosen
     */
    @GetMapping("/inventoryUpdater")
    public String inventoryUpdater(Model model) {
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "inventoryUpdater";
    }

    /**
     * Triggers the actual act of running the sync_inventory script with the passed in supplier
     * @param supplier Supplier that is to be run with the sync_inventory script
     * @return A success screen if there are no errors thrown during the process of running the screen
     */
    @PostMapping("/updateInventory")
    public String updateInventory(@ModelAttribute Supplier supplier) {
        System.out.println("STARTED RUNNING SYNC_INVENTORY.PY");
        inventoryUpdater.updateInventory(supplier.getSupplierId());
        System.out.println("FINISHED RUNNING SYNC_INVENTORY.PY");
        return "success";
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

package com.indiraactive.fulfillmentplatform.controller;

import com.indiraactive.fulfillmentplatform.dao.scriptRunAuditEntry.ScriptRunAuditEntry;
import com.indiraactive.fulfillmentplatform.dao.scriptRunAuditEntry.ScriptRunAuditEntryRepository;
import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import com.indiraactive.fulfillmentplatform.dao.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.service.inventoryUpdater.InventoryUpdater;
import com.indiraactive.fulfillmentplatform.service.inventoryUpdater.InventoryUpdaterManagerImpl;
import com.indiraactive.fulfillmentplatform.viewModel.RunHistoryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

public class InventoryUpdaterController {
    @Autowired
    private InventoryUpdaterManagerImpl inventoryUpdaterManager;

    /**
     * Lets a user choose a supplier and update inventory based on what that selected supplier has
     * @param model Helps populate the view, automatically passed in
     * @return View that lets a user pick a supplier and run the sync_inventory script with parameters from the supplier chosen
     */
    @GetMapping("/inventoryUpdater")
    public String inventoryUpdater(Model model) {
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("suppliers", inventoryUpdaterManager.getSuppliersToUpdate());
        return "inventoryUpdater";
    }

    /**
     * Triggers the actual act of running the sync_inventory script with the passed in supplier
     * @param supplier Supplier that is to be run with the sync_inventory script
     * @return A success screen if there are no errors thrown during the process of running the screen
     */
    @PostMapping("/updateInventory")
    public String updateInventory(@ModelAttribute Supplier supplier) {
        if (inventoryUpdaterManager.updateInventory(supplier)) {
            return "success";
        }
        return "error";
    }

    @RequestMapping(value="runHistory", method= RequestMethod.GET)
    public String runHistory(Model model, @RequestParam(value = "order", required = false) String order,
                             @RequestParam(value = "property", required = false) String property) {
        model.addAttribute("runHistoryViewModels", inventoryUpdaterManager.getRunHistory(order, property));

        return "runHistory";
    }
}

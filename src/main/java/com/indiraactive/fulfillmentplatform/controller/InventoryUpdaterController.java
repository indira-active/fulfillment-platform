package com.indiraactive.fulfillmentplatform.controller;

import com.indiraactive.fulfillmentplatform.dao.scriptRunAuditEntry.ScriptRunAuditEntry;
import com.indiraactive.fulfillmentplatform.dao.scriptRunAuditEntry.ScriptRunAuditEntryRepository;
import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import com.indiraactive.fulfillmentplatform.dao.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.service.InventoryUpdater;
import com.indiraactive.fulfillmentplatform.viewModel.RunHistoryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

public class InventoryUpdaterController {
    @Autowired
    private ScriptRunAuditEntryRepository scriptRunAuditEntryRepository;

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
        try {
            System.out.println("STARTED RUNNING SYNC_INVENTORY.PY");
            inventoryUpdater.updateInventory(supplier.getSupplierId());
            System.out.println("FINISHED RUNNING SYNC_INVENTORY.PY");
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping(value="runHistory", method= RequestMethod.GET)
    public String runHistory(Model model, @RequestParam(value = "order", required = false) String order,
                             @RequestParam(value = "property", required = false) String property) {
        List<Supplier> supplierRepositories = new LinkedList<>();
        supplierRepository.findAll().forEach(supplierRepositories::add);

        List<ScriptRunAuditEntry> scriptRunAuditEntries = new LinkedList<>();
        if (order != null) {
            if (order.equalsIgnoreCase("asc") && property.equalsIgnoreCase("startDate")) {
                scriptRunAuditEntries.addAll(scriptRunAuditEntryRepository.findAllByOrderByStartDateTimeAsc());
            } else if (order.equalsIgnoreCase("desc") && property.equalsIgnoreCase("startDate")) {
                scriptRunAuditEntries.addAll(scriptRunAuditEntryRepository.findAllByOrderByStartDateTimeDesc());
            } else if (order.equalsIgnoreCase("asc") && property.equalsIgnoreCase("endDate")) {
                scriptRunAuditEntries.addAll(scriptRunAuditEntryRepository.findAllByOrderByFinishDateTimeAsc());
            } else if (order.equalsIgnoreCase("desc") && property.equalsIgnoreCase("endDate")) {
                scriptRunAuditEntries.addAll(scriptRunAuditEntryRepository.findAllByOrderByFinishDateTimeDesc());
            }
        } else {
            scriptRunAuditEntries.addAll(scriptRunAuditEntryRepository.findAll());
        }

        RunHistoryViewModel runHistoryViewModel = new RunHistoryViewModel(scriptRunAuditEntries, supplierRepositories);
        model.addAttribute("runHistoryViewModels", runHistoryViewModel.getRunHistoryModel());

        return "runHistory";
    }
}

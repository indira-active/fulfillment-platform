package com.indiraactive.fulfillmentplatform.controller;

import com.indiraactive.fulfillmentplatform.viewModel.RunHistoryViewModel;
import com.indiraactive.fulfillmentplatform.dao.scriptRunAuditEntry.ScriptRunAuditEntryRepository;
import com.indiraactive.fulfillmentplatform.dao.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.dao.scriptRunAuditEntry.ScriptRunAuditEntry;
import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

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
     * Provides the consumers with the home page of the fulfillment platform web app
     * @return Index page that links to other parts of the fulfillment platform
     */
    @RequestMapping("/")
    public String home() {
        return "index";
    }
}

package com.indiraactive.fulfillmentplatform.service;

import com.indiraactive.fulfillmentplatform.dal.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.dal.scriptRunAuditEntry.ScriptRunAuditEntryFactory;
import com.indiraactive.fulfillmentplatform.factory.SyncInventoryArgumentsFactory;
import com.indiraactive.fulfillmentplatform.utility.CommandLineRunner.*;
import com.indiraactive.fulfillmentplatform.utility.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Runs the sync_inventory script seen here:
 * https://gist.github.com/sharoonthomas/ada536f3f14c7d16ac8d3d3876870f3d
 */
@Component
public class InventoryUpdater {
    /**
     * Finds the path to a script given a script name.
     */
    @Autowired
    private ScriptPathFinder scriptPathFinder;

    /**
     * Data access abstraction to database for suppliers. Used specifically in this class to retrieve a
     * supplier based on a provided id.
     */
    @Autowired
    private SupplierRepository supplierRepository;

    /**
     * Runs commands in a shell
     */
    @Autowired
    private CommandLineRunner commandLineRunner;

    /**
     * Used to log information about the sync inventory run
     */
    @Autowired
    private AuditLoggerService auditLoggerService;

    /**
     * Runs the sync_inventory.py script directly by running the script with python in a process with arguments that
     * are based on a supplier id that is passed into the method.
     * @param supplierId The id of the supplier that will be used for the commandline arguments for the
     *                   sync_inventory.py script.
     */
    public ScriptCompletionCode updateInventory(int supplierId) throws Exception {
        System.out.println("Preparing to update inventory");
        System.out.println("Retrieving supplier information");
        String inventoryUpdaterScriptPath = getInventoryUpdaterScriptPath();
        String args = SyncInventoryArgumentsFactory.getArgs(supplierRepository.findOne(supplierId)).trim();

        if (inventoryUpdaterScriptPath != null ) {
            try {
                String commandToExecute = "python " + inventoryUpdaterScriptPath + " " + args;
                long startDateTime = System.currentTimeMillis();
                ScriptCompletionCode scriptCompletionCode = commandLineRunner.executeCommand(commandToExecute);
                long endDateTime = System.currentTimeMillis();
                System.out.println("Completed running script with scriptCompletionCode: " + scriptCompletionCode);
                String userTriggered = "anonymous"; // TODO: Need to dedicate work to implementing this, maybe represent w/ Jira ticket.
                auditLoggerService.logScriptRun(ScriptRunAuditEntryFactory.createScriptRunAuditEntry(supplierId, startDateTime, endDateTime, userTriggered, scriptCompletionCode.toString()));

                return scriptCompletionCode;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        throw new Exception("Unable to update inventory, an error occurred");
    }

    /**
     * Gets the path to the inventory updater script. Utilizes the ScriptPathFinder
     * to find the path.
     * @return Path to the inventory updater script.
     */
    public String getInventoryUpdaterScriptPath() {
        String inventoryUpdaterScriptPath = null;
        try {
            inventoryUpdaterScriptPath = scriptPathFinder.getPath(ScriptPathFinder.ScriptName.SYNC_INVENTORY);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

        return inventoryUpdaterScriptPath;
    }
}

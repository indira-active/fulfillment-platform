package com.indiraactive.fulfillmentplatform.service;

import com.indiraactive.fulfillmentplatform.dal.SupplierRepository;
import com.indiraactive.fulfillmentplatform.factory.SyncInventoryArgumentsFactory;
import com.indiraactive.fulfillmentplatform.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Runs the sync_inventory script seen here:
 * https://gist.github.com/sharoonthomas/ada536f3f14c7d16ac8d3d3876870f3d
 */
@Component
public class InventoryUpdater {
    /**
     * Path where the sync_inventory.py script is located. Retrieved from the application.properties file
     */
    @Value("${inventoryUpdaterScriptPath}") // TODO: Verify everyone is in agreement with how the scripts are loaded
    private String inventoryUpdaterScriptPath;   // TODO: Supplier service to provide us w/ supplier info to update inventory from

    /**
     * Data access abstraction to database for suppliers. Used specifically in this class to retrieve a
     * supplier based on a provided id.
     */
    @Autowired
    private SupplierRepository supplierRepository;

    /**
     * Runs the sync_inventory.py script directly by running the script with python in a process with arguments that
     * are based on a supplier id that is passed into the method.
     * @param supplierId The id of the supplier that will be used for the commandline arguments for the
     *                   sync_inventory.py script.
     */
    public void updateInventory(int supplierId) { //TODO: On app start up guarantee that the script is there and is valid.
        System.out.println("Preparing to update inventory");
        System.out.println("Retrieving supplier information");
        Supplier supplier = supplierRepository.findOne(supplierId);
        String args = SyncInventoryArgumentsFactory.getArgs(supplier).trim();
        try {
            String commandToExecute = "python " + inventoryUpdaterScriptPath + " " + args;
            System.out.println("Command to be executed: " + commandToExecute);
            Process process = Runtime.getRuntime().exec(commandToExecute);
            String line;
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println("***FROM CONSOLE: " + line);
            }
            input.close();
        } catch(IOException ioException) {
            ioException.printStackTrace();
            System.out.println("Error, unable to find python script."); // TODO: Add better error notification
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

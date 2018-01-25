package com.indiraactive.stockupdaterserver.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;

// http://support.fulfil.io/article/496-using-vendors-shopify-for-inventory-feed

@Component
public class InventoryUpdater {
    // http://support.fulfil.io/article/496-using-vendors-shopify-for-inventory-feed Want to eventually utilize this script
    @Value("${inventoryUpdaterScriptPath}") // TODO: Verify everyone is in agreement with how the scripts are loaded
    private String inventoryUpdaterScriptPath;   // TODO: Supplier service to provide us w/ supplier info to update inventory from

    public void updateInventory() { //TODO: On app start up guarantee that the script is there and is valid.
        System.out.println("Preparing to update inventory");
        try {
            Process process = Runtime.getRuntime().exec("python " + inventoryUpdaterScriptPath);
        } catch(IOException ioException) {
            ioException.printStackTrace();
            System.out.println("Error, unable to find python script."); // TODO: Add better error notification
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

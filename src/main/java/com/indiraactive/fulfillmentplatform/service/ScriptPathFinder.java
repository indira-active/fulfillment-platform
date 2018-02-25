package com.indiraactive.fulfillmentplatform.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Finds a path for a given script
 */
@Component
public class ScriptPathFinder {

    /**
     * Enum types that represents a script to retrieve.
     */
    public enum ScriptName {
        /**
         * Represents the the sync_inventory.py script provided to Indira Active from Fulfil.
         * Eventually we can probably retrieve these values from an external data source.
         */
        SYNC_INVENTORY
    }

    /**
     * Path where the sync_inventory.py script is located. Retrieved from the application.properties file
     * in the future we will likely implement this script logic ourselves.
     */
    @Value("${inventoryUpdaterScriptPath}")
    private String inventoryUpdaterScriptPath;

    /**
     * Gets the path for a given script constant name
     * @param syncInventory The script to get the path for
     * @return File path to the requested script
     * @throws Exception If the scriptName does not exist
     */
    public String getPath(ScriptName syncInventory) throws Exception {
        if (syncInventory.equals(ScriptName.SYNC_INVENTORY)) {
            return inventoryUpdaterScriptPath;
        }

        throw new Exception("Unable to find path to requested script named: " + ScriptName.SYNC_INVENTORY.toString());
    }


}

package com.indiraactive.fulfillmentplatform.factory;

import com.indiraactive.fulfillmentplatform.model.Supplier;

//TODO: Reconsider the name of this class

/**
 * Creates arguments for sync_inventory script given a supplier
 */
public class SyncInventoryArgumentsFactory {
    /**
     * Creates a string that represents the commandline arguments for sync_inventory script based on a supplier. The
     * arguments are being formed based on the following confluence documentation:
     * https://indiraactive.atlassian.net/wiki/spaces/SP/pages/10518531/Business+Automation+Scripts
     * @param supplier Supplier to create sync_inventory args from
     * @return A string that was derived from the passed in supplier that represents a set of commandline arguments
     * for the sync_inventory script
     */
    public static String getArgs(Supplier supplier) {
        return
            supplier.getShopifyApiKey() + " " +
            supplier.getShopifyPassword() + " " +
            supplier.getShopName() + " " +
            supplier.getFulfillSubdomain() + " " +
            supplier.getFulfillApiKey() + " " +
            supplier.getSupplierCode() + " ";
    }
}

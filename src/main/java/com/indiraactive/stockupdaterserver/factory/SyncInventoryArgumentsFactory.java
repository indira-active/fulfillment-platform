package com.indiraactive.stockupdaterserver.factory;

import com.indiraactive.stockupdaterserver.model.Supplier;

public class SyncInventoryArgumentsFactory {
    // Arguments being formed based on https://indiraactive.atlassian.net/wiki/spaces/SP/pages/10518531/Business+Automation+Scripts
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

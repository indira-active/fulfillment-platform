package com.indiraactive.stockupdaterserver.factory;

import com.indiraactive.stockupdaterserver.model.Supplier;
import org.springframework.beans.factory.FactoryBean;

public class SyncInventoryArgumentsFactory {
    // Arguments being formed based on https://indiraactive.atlassian.net/wiki/spaces/SP/pages/10518531/Business+Automation+Scripts
    public static String getArgs(Supplier supplier) {
        return
            supplier.getShopify_api_key() + " " +
            supplier.getShopify_password() + " " +
            supplier.getShop_name() + " " +
            supplier.getFulfil_subdomain() + " " +
            supplier.getFulfil_api_key() + " " +
            supplier.getSupplier_code() + " ";
    }
}

package com.indiraactive.fulfillmentplatform.model;

import com.indiraactive.fulfillmentplatform.dal.SupplierRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class SupplierTests {

    @Test
    public void testSupplierId() {
        Supplier supplier = new Supplier();
        int expectedSupplierId = 1;
        supplier.setSupplierId(expectedSupplierId);
        String expectedFulfillApiKey = "expectedFulfillApiKey";
        supplier.setFulfillApiKey(expectedFulfillApiKey);
        String expectedFulfillSubdomain = "expectedFulfillSubdomain";
        supplier.setFulfillSubdomain(expectedFulfillSubdomain);
        String expectedShopifyApiKey = "expectedShopifyApiKey";
        supplier.setShopifyApiKey(expectedShopifyApiKey);
        String expectedShopifyPassword = "expectedShopifyPassword";
        supplier.setShopifyPassword(expectedShopifyPassword);
        String expectedShopName = "expectedShopName";
        supplier.setShopName(expectedShopName);
        String expectedSupplierCode = "expectedSupplierCode";
        supplier.setSupplierCode(expectedSupplierCode);
        String expectedSupplierName = "expectedSupplierName";
        supplier.setSupplierName(expectedSupplierName);

        int actualSupplierId = supplier.getSupplierId();
        String actualFulfillApiKey = supplier.getFulfillApiKey();
        String actualFulfillSubdomain = supplier.getFulfillSubdomain();
        String actualShopifyApiKey = supplier.getShopifyApiKey();
        String actualShopifyPassword = supplier.getShopifyPassword();
        String actualShopName = supplier.getShopName();
        String actualSupplierCode = supplier.getSupplierCode();
        String actualSupplierName = supplier.getSupplierName();

        assertEquals(expectedSupplierId, actualSupplierId);
        assertEquals(expectedFulfillApiKey, actualFulfillApiKey);
        assertEquals(expectedFulfillSubdomain, actualFulfillSubdomain);
        assertEquals(expectedShopifyApiKey, actualShopifyApiKey);
        assertEquals(expectedShopifyPassword, actualShopifyPassword);
        assertEquals(expectedShopName, actualShopName);
        assertEquals(expectedSupplierCode, actualSupplierCode);
        assertEquals(expectedSupplierName, actualSupplierName);
    }
}

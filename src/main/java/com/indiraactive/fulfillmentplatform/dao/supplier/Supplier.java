package com.indiraactive.fulfillmentplatform.dao.supplier;

import javax.persistence.*;

/**
 * Represents a specific supplier. Used to generate the supplier table in the
 * fulfillment platform database. All properties besides supplier_id and supplier_name
 * are derived from the sync_inventory section on the following confluence page:
 * https://indiraactive.atlassian.net/wiki/spaces/SP/pages/10518531/Business+Automation+Scripts
 * The java docs for everything besides those two attributes are derived from the above url.
 */
@Entity
@Table(name = "supplier")
public class Supplier {
    /**
     * Automatically increments in the database and is the primary key. The id is unique for
     * each supplier
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "supplier_id", length = 64, updatable = false)
    private Integer supplierId;

    /**
     * Name that the user gave the set of supplier information.
     */
    @Column(name = "supplier_name", nullable = false)
    private String supplierName;

    /**
     * The API Key from a Shopify Private app
     * (https://help.shopify.com/manual/apps/private-apps#generate-credentials-from-the-shopify-admin)
     */
    @Column(name = "shopify_api_key", nullable = false)
    private String shopifyApiKey;

    /**
     * Password from the private app
     */
    @Column(name = "shopify_password", nullable = false)
    private String shopifyPassword;

    /**
     * The shopify store name. Usually the first part of the .myshopify.com URL you use to access the store admin.
     */
    @Column(name = "shop_name", nullable = false)
    private String shopName;

    /**
     * The subdomain of your fulfil instance.
     */
    @Column(name = "fulfill_subdomain", nullable = false)
    private String fulfillSubdomain;

    /**
     * The API key of your fulfil instance. The user must have permission to read and write product suppliers.
     */
    @Column(name = "fulfill_api_key", nullable = false)
    private String fulfillApiKey;

    /**
     * The code of the supplier (who own the shopify store). The code can be found on the top right card on the
     * supplier contact. Usually a number.
     */
    @Column(name = "supplier_code", nullable = false)
    private String supplierCode;

    public Supplier() { }

    public Supplier(Integer supplierId, String supplierName, String shopifyApiKey, String shopifyPassword, String shopName, String fulfillSubdomain, String fulfillApiKey, String supplierCode) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.shopifyApiKey = shopifyApiKey;
        this.shopifyPassword = shopifyPassword;
        this.shopName = shopName;
        this.fulfillSubdomain = fulfillSubdomain;
        this.fulfillApiKey = fulfillApiKey;
        this.supplierCode = supplierCode;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getShopifyApiKey() {
        return shopifyApiKey;
    }

    public void setShopifyApiKey(String shopifyApiKey) {
        this.shopifyApiKey = shopifyApiKey;
    }

    public String getShopifyPassword() {
        return shopifyPassword;
    }

    public void setShopifyPassword(String shopifyPassword) {
        this.shopifyPassword = shopifyPassword;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getFulfillSubdomain() {
        return fulfillSubdomain;
    }

    public void setFulfillSubdomain(String fulfillSubdomain) {
        this.fulfillSubdomain = fulfillSubdomain;
    }

    public String getFulfillApiKey() {
        return fulfillApiKey;
    }

    public void setFulfillApiKey(String fulfillApiKey) {
        this.fulfillApiKey = fulfillApiKey;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}

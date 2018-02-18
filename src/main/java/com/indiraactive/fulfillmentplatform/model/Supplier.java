package com.indiraactive.fulfillmentplatform.model;

import javax.persistence.*;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "supplier_id", length = 64, updatable = false)
    private Integer supplierId;

    @Column(name = "supplier_name", nullable = false)
    private String supplierName;

    @Column(name = "shopify_api_key", nullable = false)
    private String shopifyApiKey;

    @Column(name = "shopify_password", nullable = false)
    private String shopifyPassword;

    @Column(name = "shop_name", nullable = false)
    private String shopName;

    @Column(name = "fulfill_subdomain", nullable = false)
    private String fulfillSubdomain;

    @Column(name = "fulfill_api_key", nullable = false)
    private String fulfillApiKey;

    @Column(name = "supplier_code", nullable = false)
    private String supplierCode;

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

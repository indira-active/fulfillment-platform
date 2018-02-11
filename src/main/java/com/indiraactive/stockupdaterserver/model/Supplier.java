package com.indiraactive.stockupdaterserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer supplier_id;
    private String shopify_api_key;
    private String shopify_password;
    private String shop_name;
    private String fulfil_subdomain;
    private String fulfil_api_key;
    private String supplier_code;
    private String alias;

    public Integer getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(Integer supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getShopify_api_key() {
        return shopify_api_key;
    }

    public void setShopify_api_key(String shopify_api_key) {
        this.shopify_api_key = shopify_api_key;
    }

    public String getShopify_password() {
        return shopify_password;
    }

    public void setShopify_password(String shopify_password) {
        this.shopify_password = shopify_password;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getFulfil_subdomain() {
        return fulfil_subdomain;
    }

    public void setFulfil_subdomain(String fulfil_subdomain) {
        this.fulfil_subdomain = fulfil_subdomain;
    }

    public String getFulfil_api_key() {
        return fulfil_api_key;
    }

    public void setFulfil_api_key(String fulfil_api_key) {
        this.fulfil_api_key = fulfil_api_key;
    }

    public String getSupplier_code() {
        return supplier_code;
    }

    public void setSupplier_code(String supplier_code) {
        this.supplier_code = supplier_code;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}

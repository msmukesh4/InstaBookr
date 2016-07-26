package com.example.instabookr.models;

/**
 * Created by mukesh on 12/7/16.
 */
public class Service {
    String uuid;
    String shop_uuid;
    int service_type_id;
    String service_name;
    int cost;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getShop_uuid() {
        return shop_uuid;
    }

    public void setShop_uuid(String shop_uuid) {
        this.shop_uuid = shop_uuid;
    }

    public int getService_type_id() {
        return service_type_id;
    }

    public void setService_type_id(int service_type_id) {
        this.service_type_id = service_type_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}

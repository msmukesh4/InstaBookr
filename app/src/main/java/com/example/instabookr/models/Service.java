package com.example.instabookr.models;

/**
 * Created by mukesh on 12/7/16.
 */
public class Service {
    public int id;
    String uuid;
    String shop_uuid;
    int service_type_id;
    String service_name;
    int cost;
    String icon;

    public int getDiscounted_cost() {
        return discounted_cost;
    }

    public void setDiscounted_cost(int discounted_cost) {
        this.discounted_cost = discounted_cost;
    }

    int discounted_cost=0;

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", shop_uuid='" + shop_uuid + '\'' +
                ", service_type_id=" + service_type_id +
                ", service_name='" + service_name + '\'' +
                ", cost=" + cost +
                ", icon='" + icon + '\'' +
                ", discounted_cost=" + discounted_cost +
                ", offer_uuid='" + offer_uuid + '\'' +
                '}';
    }

    String offer_uuid;

    public String getIcon() {
        return icon;
    }

    public int getDiscount() {
        return discounted_cost;
    }

    public Service(){
        // default constructor is needed as we have other constructor too
    }

    public Service(String shop_uuid, String uuid, int service_type_id, String service_name, int cost, String icon, int discounted_cost) {
        this.uuid = uuid;
        this.discounted_cost = discounted_cost;
        this.icon = icon;
        this.cost = cost;
        this.service_name = service_name;
        this.service_type_id = service_type_id;
        this.shop_uuid = shop_uuid;
    }

    public void setDiscount(int discounted_cost) {

        this.discounted_cost = discounted_cost;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Service(String service_name, int id){
        this.id = id;
        this.service_name = service_name;
    }

    public Service(String shop_uuid, String uuid, int service_type_id, String service_name, int cost, String icon) {
        this.uuid = uuid;
        this.icon = icon;
        this.cost = cost;
        this.service_name = service_name;
        this.service_type_id = service_type_id;
        this.shop_uuid = shop_uuid;
    }

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

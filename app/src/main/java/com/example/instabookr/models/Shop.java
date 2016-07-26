package com.example.instabookr.models;

import java.util.ArrayList;

/**
 * Created by mukesh on 12/7/16.
 */
public class Shop {

    String shop_name;
    String icon;
    String banner;
    String uuid;
    String offer_id;
    String[] time_slot_ids;
    String contact_number1;
    String contact_number2;
    Integer location_id;
    double latitude = 0.0;
    double longitude = 0.0;
    String caption;
    String details;

    public  ArrayList<Service> getShop_service_list() {
        return shop_service_list;
    }

    public void setShop_service_list(ArrayList<Service> shop_service_list) {
        this.shop_service_list = shop_service_list;
    }

    String address;
    ArrayList<Service> shop_service_list = new ArrayList<Service>();

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public String[] getTime_slot_ids() {
        return time_slot_ids;
    }

    public void setTime_slot_ids(String[] time_slot_ids) {
        this.time_slot_ids = time_slot_ids;
    }

    public String getContact_number1() {
        return contact_number1;
    }

    public void setContact_number1(String contact_number1) {
        this.contact_number1 = contact_number1;
    }

    public String getContact_number2() {
        return contact_number2;
    }

    public void setContact_number2(String contact_number2) {
        this.contact_number2 = contact_number2;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

package com.example.instabookr.models;

import java.util.Date;

/**
 * Created by mukesh on 12/7/16.
 */
public class Booking {

    Shop shop = new Shop();
    Service service = new Service();
    String uuid;
    Date start_date;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    String time_slots;
    String booking_token;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getTime_slots() {
        return time_slots;
    }

    public void setTime_slots(String time_slots) {
        this.time_slots = time_slots;
    }

    public String getBooking_token() {
        return booking_token;
    }

    public void setBooking_token(String booking_token) {
        this.booking_token = booking_token;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    int cost;

}

package com.example.instabookr.models;

/**
 * Created by mukesh on 26/7/16.
 */
public class Offer {
    public String offersHeader;
    public String offerDesc;

    public Offer(String offersHeader, String offerDesc) {
        this.offersHeader = offersHeader;
        this.offerDesc = offerDesc;
    }

    public String getOffersHeader() {
        return offersHeader;
    }

    public void setOffersHeader(String offersHeader) {
        this.offersHeader = offersHeader;
    }

    public String getOfferDesc() {
        return offerDesc;
    }

    public void setOfferDesc(String offerDesc) {
        this.offerDesc = offerDesc;
    }
}

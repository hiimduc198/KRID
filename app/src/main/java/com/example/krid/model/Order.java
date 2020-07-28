package com.example.krid.model;

import java.util.Date;

public class Order {
    private String id;
    private String camId;
    private String infId;
    private String ordStatusId;
    private String address;

    public Order() {
    }

    public Order(String id, String camId, String infId, String ordStatusId, String address) {
        this.id = id;
        this.camId = camId;
        this.infId = infId;
        this.ordStatusId = ordStatusId;
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCamId(String camId) {
        this.camId = camId;
    }

    public void setInfId(String infId) {
        this.infId = infId;
    }

    public void setOrdStatusId(String ordStatusId) {
        this.ordStatusId = ordStatusId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getCamId() {
        return camId;
    }

    public String getInfId() {
        return infId;
    }

    public String getOrdStatusId() {
        return ordStatusId;
    }

    public String getAddress() {
        return address;
    }
}

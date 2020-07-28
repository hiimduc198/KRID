package com.example.krid.model;

public class InfluencerTravelCity {
    private String id;
    private String infId;
    private String cityId;

    public InfluencerTravelCity() {
    }

    public InfluencerTravelCity(String id, String infId, String cityId) {
        this.id = id;
        this.infId = infId;
        this.cityId = cityId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInfId(String infId) {
        this.infId = infId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getId() {
        return id;
    }

    public String getInfId() {
        return infId;
    }

    public String getCityId() {
        return cityId;
    }
}

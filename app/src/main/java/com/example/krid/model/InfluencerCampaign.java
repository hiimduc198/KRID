package com.example.krid.model;

public class InfluencerCampaign {
    private String id;
    private String infId;
    private String camId;
    private String status;

    public InfluencerCampaign(String id, String infId, String camId, String status) {
        this.id = id;
        this.infId = infId;
        this.camId = camId;
        this.status = status;
    }

    public InfluencerCampaign() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInfId(String infId) {
        this.infId = infId;
    }

    public void setCamId(String camId) {
        this.camId = camId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getInfId() {
        return infId;
    }

    public String getCamId() {
        return camId;
    }

    public String getStatus() {
        return status;
    }
}

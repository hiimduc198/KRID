package com.example.krid.model;

public class InfluencerCampaign {
    private String id;
    private String infId;
    private String camId;
    private String infStatusId;

    public InfluencerCampaign(String id, String infId, String camId, String infStatusId) {
        this.id = id;
        this.infId = infId;
        this.camId = camId;
        this.infStatusId = infStatusId;
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

    public void setInfStatusId(String infStatusId) {
        this.infStatusId = infStatusId;
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

    public String getInfStatusId() {
        return infStatusId;
    }
}

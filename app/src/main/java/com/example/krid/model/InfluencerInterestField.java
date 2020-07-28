package com.example.krid.model;

public class InfluencerInterestField {
    private String id;
    private String infId;
    private String fieldId;

    public InfluencerInterestField() {
    }

    public InfluencerInterestField(String id, String infId, String fieldId) {
        this.id = id;
        this.infId = infId;
        this.fieldId = fieldId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInfId(String infId) {
        this.infId = infId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getId() {
        return id;
    }

    public String getInfId() {
        return infId;
    }

    public String getFieldId() {
        return fieldId;
    }
}

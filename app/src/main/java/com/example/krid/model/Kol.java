package com.example.krid.model;

import java.util.Date;

public class Kol {
    private String id;
    private String name;
    private Date dob;
    private String cityId;
    private String facebook;
    private String facebookFollowers;
    private String numbOfInteractions;
    private int gender;
    private String jobId;
    private String groupId;
    private String description;

    public Kol() {
    }

    public Kol(String id, String name, Date dob, String cityId, String facebook, String facebookFollowers, String numbOfInteractions, int gender, String jobId, String groupId, String description) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.cityId = cityId;
        this.facebook = facebook;
        this.facebookFollowers = facebookFollowers;
        this.numbOfInteractions = numbOfInteractions;
        this.gender = gender;
        this.jobId = jobId;
        this.groupId = groupId;
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setFacebookFollowers(String facebookFollowers) {
        this.facebookFollowers = facebookFollowers;
    }

    public void setNumbOfInteractions(String numbOfInteractions) {
        this.numbOfInteractions = numbOfInteractions;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return dob;
    }

    public String getCityId() {
        return cityId;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getFacebookFollowers() {
        return facebookFollowers;
    }

    public String getNumbOfInteractions() {
        return numbOfInteractions;
    }

    public int getGender() {
        return gender;
    }

    public String getJobId() {
        return jobId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getDescription() {
        return description;
    }
}

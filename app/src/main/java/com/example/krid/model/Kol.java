package com.example.krid.model;

import java.io.Serializable;
import java.util.Date;

public class Kol implements Serializable {
    private String id;
    private String name;
    private Date dob;
    private String cityId;
    private String facebook;
    private int facebookFollowers;
    private int numOfInteractions;
    private String gender;
    private String jobId;
    private String groupId;
    private String description;
    private String image;
    private String phone;

    public Kol() {
    }

    public Kol(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Kol(String id, String name, Date dob, String cityId, String facebook, int facebookFollowers, int numOfInteractions, String gender, String jobId, String groupId, String description, String image) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.cityId = cityId;
        this.facebook = facebook;
        this.facebookFollowers = facebookFollowers;
        this.numOfInteractions = numOfInteractions;
        this.gender = gender;
        this.jobId = jobId;
        this.groupId = groupId;
        this.description = description;
        this.image = image;
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

    public void setFacebookFollowers(int facebookFollowers) {
        this.facebookFollowers = facebookFollowers;
    }

    public void setNumOfInteractions(int numOfInteractions) {
        this.numOfInteractions = numOfInteractions;
    }

    public void setGender(String gender) {
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

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
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

    public int getFacebookFollowers() {
        return facebookFollowers;
    }

    public int getNumOfInteractions() {
        return numOfInteractions;
    }

    public String getGender() {
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

package com.example.krid.model;

import java.io.Serializable;
import java.util.Date;

public class Influencer implements Serializable {
    private String id;
    private String name;
    private String email;
    private String phone;
    private Date dob;
    private String address;
    private String cityId;
    private String facebook;
    private int facebookFollowers;
    private int numbOfInteractions;
    private int gender;
    private String jobId;
    private String groupId;
    private String username;
    private String password;

    public Influencer() {
    }

    public Influencer(String id, String name, String email, String phone, Date dob, String address, String cityId, String facebook, int facebookFollowers, int numbOfInteractions, int gender, String jobId, String groupId, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
        this.cityId = cityId;
        this.facebook = facebook;
        this.facebookFollowers = facebookFollowers;
        this.numbOfInteractions = numbOfInteractions;
        this.gender = gender;
        this.jobId = jobId;
        this.groupId = groupId;
        this.username = username;
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void setNumbOfInteractions(int numbOfInteractions) {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Date getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
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

    public int getNumbOfInteractions() {
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

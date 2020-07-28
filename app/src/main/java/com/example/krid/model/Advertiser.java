package com.example.krid.model;

public class Advertiser {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String companyName;
    private String website;
    private String username;
    private String password;

    public Advertiser() {
    }

    public Advertiser(String id, String name, String email, String phone, String companyName, String website, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.companyName = companyName;
        this.website = website;
        this.username = username;
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

    public String getCompanyName() {
        return companyName;
    }

    public String getWebsite() {
        return website;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

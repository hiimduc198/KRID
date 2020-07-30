package com.example.krid.model;

import java.io.Serializable;
import java.util.Date;

public class Campaign implements Serializable {
    private String id;
    private String advId;
    private String formatId;
    private String title;
    private String numOfInf;
    private Date postTime;
    private Date applyTime;
    private Date reviewTime;
    private Date approvalTime;
    private String description;
    private boolean requireAction;
    private String cityId;
    private String location;
    private String image;
    private String reward;
    private String cash;
    private String hastag;
    private String toDo;
    private String otherInfo;
    private String website;
    private String infGender;
    private String infAgeRange;
    private String infGroupId;
    private String camStatusId;

    public Campaign() {
    }

    public Campaign(String id, String advId, String formatId, String title, String numOfInf, Date postTime, Date applyTime, Date reviewTime, Date approvalTime, String description, boolean requireAction, String cityId, String location, String image, String reward, String cash, String hastag, String toDo, String otherInfo, String website, String infGender, String infAgeRange, String infGroupId, String camStatusId) {
        this.id = id;
        this.advId = advId;
        this.formatId = formatId;
        this.title = title;
        this.numOfInf = numOfInf;
        this.postTime = postTime;
        this.applyTime = applyTime;
        this.reviewTime = reviewTime;
        this.approvalTime = approvalTime;
        this.description = description;
        this.requireAction = requireAction;
        this.cityId = cityId;
        this.location = location;
        this.image = image;
        this.reward = reward;
        this.cash = cash;
        this.hastag = hastag;
        this.toDo = toDo;
        this.otherInfo = otherInfo;
        this.website = website;
        this.infGender = infGender;
        this.infAgeRange = infAgeRange;
        this.infGroupId = infGroupId;
        this.camStatusId = camStatusId;
    }

    public Campaign(String id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAdvId(String advId) {
        this.advId = advId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumOfInf(String numOfInf) {
        this.numOfInf = numOfInf;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequireAction(boolean requireAction) {
        this.requireAction = requireAction;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public void setHastag(String hastag) {
        this.hastag = hastag;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setInfGender(String infGender) {
        this.infGender = infGender;
    }

    public void setInfAgeRange(String infAgeRange) {
        this.infAgeRange = infAgeRange;
    }

    public void setInfGroupId(String infGroupId) {
        this.infGroupId = infGroupId;
    }

    public void setCamStatusId(String camStatusId) {
        this.camStatusId = camStatusId;
    }

    public String getId() {
        return id;
    }

    public String getAdvId() {
        return advId;
    }

    public String getFormatId() {
        return formatId;
    }

    public String getTitle() {
        return title;
    }

    public String getNumOfInf() {
        return numOfInf;
    }

    public Date getPostTime() {
        return postTime;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public String getDescription() {
        return description;
    }

    public boolean getRequireAction() {
        return requireAction;
    }

    public String getCityId() {
        return cityId;
    }

    public String getLocation() {
        return location;
    }

    public String getImage() {
        return image;
    }

    public String getReward() {
        return reward;
    }

    public String getCash() {
        return cash;
    }

    public String getHastag() {
        return hastag;
    }

    public String getToDo() {
        return toDo;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public String getWebsite() {
        return website;
    }

    public String getInfGender() {
        return infGender;
    }

    public String getInfAgeRange() {
        return infAgeRange;
    }

    public String getInfGroupId() {
        return infGroupId;
    }

    public String getCamStatusId() {
        return camStatusId;
    }
}

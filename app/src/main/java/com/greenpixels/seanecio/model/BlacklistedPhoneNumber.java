package com.greenpixels.seanecio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Blacklisted Phone Number model representation
 */
public class BlacklistedPhoneNumber {

    public static final String collectionName = "blacklistedPhoneNumbers";

    private String phoneNumber;
    private String description;
    private boolean active;
    private String _reportedBy;
    private String createdAt;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getReportedBy() {
        return _reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        _reportedBy = reportedBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
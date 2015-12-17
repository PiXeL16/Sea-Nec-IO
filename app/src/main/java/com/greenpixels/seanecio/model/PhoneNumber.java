package com.greenpixels.seanecio.model;

import java.util.Date;

/**
 * This class will represent the phoneNumber object in the DB
 * Used for reporting and checking blacklisted phone numbers
 */
public class PhoneNumber {

    public static final String collectionName = "phoneNumbers";

    private String phoneNumber;
    private String description;
    private boolean active;
    private String reportedBy;
    private String createdAt;

    /**
     * Class contructors
     */
    public PhoneNumber()
    {

    }

    public PhoneNumber(String phoneNumber, String description, boolean active, String reportedBy) {
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.active = active;
        this.reportedBy = reportedBy;
        this.createdAt = new Date().toString();
    }


    public PhoneNumber(String phoneNumber, String description) {
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.createdAt = new Date().toString();
    }


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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}

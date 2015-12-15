package com.greenpixels.seanecio.model;

/**
 * This class will represent the phoneNumber object in the DB
 * Used for reporting and checking blacklisted phone numbers
 */
public class PhoneNumber {

    private String phoneNumber;
    private String description;
    private boolean active;
    private String reportedBy;

    public PhoneNumber()
    {

    }

    public PhoneNumber(String phoneNumber, String description, boolean active, String reportedBy) {
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.active = active;
        this.reportedBy = reportedBy;
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
}

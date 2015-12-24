package com.greenpixels.seanecio.model;

/**
 * Blacklisted Phone Number model representation
 */
public class BlacklistedPhoneNumber {

    public static final String collectionName = "blacklistedPhoneNumbers";

    private String _phoneNumber;
    private String _description;
    private boolean _active;
    private String _reportedBy;
    private String _createdAt;

    public BlacklistedPhoneNumber(String phoneNumber, String description, boolean active, String reportedBy, String createdAt) {
        _phoneNumber = phoneNumber;
        _description = description;
        _active = active;
        _reportedBy = reportedBy;
        _createdAt = createdAt;
    }


    public static String getCollectionName() {
        return collectionName;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        _phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public boolean isActive() {
        return _active;
    }

    public void setActive(boolean active) {
        _active = active;
    }

    public String getReportedBy() {
        return _reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        _reportedBy = reportedBy;
    }

    public String getCreatedAt() {
        return _createdAt;
    }

    public void setCreatedAt(String createdAt) {
        _createdAt = createdAt;
    }
}
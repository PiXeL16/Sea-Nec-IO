package com.greenpixels.seanecio.model;

import java.util.Date;

/**
 * Report phone number model representation
 */
public class PhoneNumber {

    public static final String collectionName = "phoneNumbers";

    private String _phoneNumber;
    private String _description;
    private boolean _active;
    private boolean _isBlacklisted;
    private String _reportedBy;
    private String _createdAt;

    /**
     * Class contructors
     */
    public PhoneNumber(String phoneNumber, String description, boolean active, String reportedBy, boolean isBlacklisted) {
        this._phoneNumber = phoneNumber;
        this._description = description;
        this._active = active;
        this._isBlacklisted = isBlacklisted;
        this._reportedBy = reportedBy;
        this._createdAt = new Date().toString();
    }


    public PhoneNumber(String phoneNumber, String description) {
        this._phoneNumber = phoneNumber;
        this._description = description;
        this._createdAt = new Date().toString();
        this._active = false;
        this._isBlacklisted = false;
    }


    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this._phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public boolean isActive() {
        return _active;
    }

    public void setActive(boolean active) {
        this._active = active;
    }

    public String getReportedBy() {
        return _reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this._reportedBy = reportedBy;
    }

    public String getCreatedAt() {
        return _createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this._createdAt = createdAt;
    }

    public boolean isBlacklisted() {
        return _isBlacklisted;
    }

    public void setIsBlacklisted(boolean isBlacklisted) {
        _isBlacklisted = isBlacklisted;
    }

}

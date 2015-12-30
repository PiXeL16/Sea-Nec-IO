package com.greenpixels.seanecio.views;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * View intergace for the report phone number activity
 */
public interface ReportPhoneNumberView extends MvpView {

    public void showContent();

    public void showError(String error);

    public void showLoading();

    public void showSucceded();

}

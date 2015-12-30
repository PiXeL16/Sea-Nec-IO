package com.greenpixels.seanecio.views;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * View interface for the blacklisted phone number list
 */
public interface BlacklistedPhoneNumberListView extends MvpView {

    public void showContent();

    public void showError(String error);

    public void showLoading();

}
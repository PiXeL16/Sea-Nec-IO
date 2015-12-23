package com.greenpixels.seanecio.views;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by chris on 12/23/15.
 */
public interface BlacklistedPhoneNumberListView extends MvpView {

    public void showContent();

    public void showError(String error);

    public void showLoading();

}
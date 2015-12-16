package com.greenpixels.seanecio.views;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by chris on 12/15/15.
 */
public interface ReportPhoneNumberView extends MvpView {

    public void showContent();

    public void showError(String error);

    public void showLoading();

}

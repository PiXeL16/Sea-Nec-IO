package com.greenpixels.seanecio.view_states;

import android.os.Bundle;

import com.greenpixels.seanecio.views.ReportPhoneNumberView;
import com.hannesdorfmann.mosby.mvp.viewstate.RestoreableViewState;

/**
 * View state for the status of the report phone number activity
 */
public class ReportPhoneNumberViewState implements RestoreableViewState<ReportPhoneNumberView> {

    final int STATE_SHOW_CONTENT = 0;
    final int STATE_SHOW_LOADING = 1;
    final int STATE_SHOW_ERROR = 2;
    final int STATE_SHOW_SUCCEDED = 3;

    int state = STATE_SHOW_CONTENT;

    private String _error;

    public ReportPhoneNumberViewState() {
    }

    public void setError(String error) {
        _error = error;
    }

    @Override
    public void saveInstanceState(Bundle bundle) {

    }

    @Override
    public RestoreableViewState<ReportPhoneNumberView> restoreInstanceState(Bundle bundle) {
        return null;
    }

    @Override
    public void apply(ReportPhoneNumberView reportPhoneNumberView, boolean b) {
        switch (state) {
            case STATE_SHOW_LOADING:
                reportPhoneNumberView.showLoading();
                break;

            case STATE_SHOW_ERROR:
                reportPhoneNumberView.showError(_error);
                break;

            case STATE_SHOW_CONTENT:
                reportPhoneNumberView.showContent();
                break;

            case STATE_SHOW_SUCCEDED:
                reportPhoneNumberView.showSucceded();
                break;
        }
    }

    public void setShowContent() {
        state = STATE_SHOW_CONTENT;
    }

    public void setShowError() {
        state = STATE_SHOW_ERROR;
    }

    public void setShowLoading() {
        state = STATE_SHOW_LOADING;
    }

    public void setShowSucceded() {
        state = STATE_SHOW_SUCCEDED;
    }

}

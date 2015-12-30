package com.greenpixels.seanecio.view_states;

import android.os.Bundle;

import com.greenpixels.seanecio.views.BlacklistedPhoneNumberListView;
import com.hannesdorfmann.mosby.mvp.viewstate.RestoreableViewState;

/**
 * Created by chris on 12/23/15.
 * View state for the blacklisted phone number activity. Not used at the momment
 */
public class BlacklistedPhoneNumberListViewState implements RestoreableViewState<BlacklistedPhoneNumberListView> {

    final int STATE_SHOW_CONTENT = 0;
    final int STATE_SHOW_LOADING = 1;
    final int STATE_SHOW_ERROR = 2;

    int state = STATE_SHOW_CONTENT;

    private String _error;

    public BlacklistedPhoneNumberListViewState() {
    }

    public void setError(String error) {
        _error = error;
    }

    @Override
    public void saveInstanceState(Bundle bundle) {

    }

    @Override
    public RestoreableViewState<BlacklistedPhoneNumberListView> restoreInstanceState(Bundle bundle) {
        return null;
    }

    @Override
    public void apply(BlacklistedPhoneNumberListView reportPhoneNumberView, boolean b) {
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

}

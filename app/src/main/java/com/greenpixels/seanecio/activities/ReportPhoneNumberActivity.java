package com.greenpixels.seanecio.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.greenpixels.seanecio.R;
import com.greenpixels.seanecio.components.AppComponent;
import com.greenpixels.seanecio.components.DaggerReportPhoneNumberComponent;
import com.greenpixels.seanecio.components.ReportPhoneNumberComponent;
import com.greenpixels.seanecio.general_classes.MainApp;
import com.greenpixels.seanecio.modules.ContextProvider;
import com.greenpixels.seanecio.modules.FirebaseProvider;
import com.greenpixels.seanecio.modules.UtilsProvider;
import com.greenpixels.seanecio.presenters.ReportPhoneNumberPresenter;
import com.greenpixels.seanecio.telephony.PhoneNumberLocalPersistence;
import com.greenpixels.seanecio.utils.AlertUtils;
import com.greenpixels.seanecio.utils.StringUtils;
import com.greenpixels.seanecio.view_states.ReportPhoneNumberViewState;
import com.greenpixels.seanecio.views.ReportPhoneNumberView;
import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.RestoreableViewState;

import butterknife.Bind;
import butterknife.OnClick;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

/**
 * Activity to report a phone number
 */

public class ReportPhoneNumberActivity extends MvpViewStateActivity<ReportPhoneNumberView, ReportPhoneNumberPresenter> implements ReportPhoneNumberView {

    @Bind(R.id.edittext_phonenumber)
    EditText _editTextPhoneNumber;
    @Bind(R.id.edittext_description)
    EditText _editTextDescription;
    @Bind(R.id.btn_report_phonenumber)
    Button _btnReportPhoneNumber;
    @Bind(R.id.progressBar)
    SmoothProgressBar _progressBar;


    private ReportPhoneNumberComponent _component;

    /**
     * Inject the dependencies in the activity
     */
    @Override
    public void injectDependencies(){
        AppComponent appComponent = MainApp.get(this).appComponent();

        _component = DaggerReportPhoneNumberComponent.builder()
                .appComponent(appComponent)
                .contextProvider(new ContextProvider(this))
                .utilsProvider(new UtilsProvider())
                .firebaseProvider(new FirebaseProvider())
                .build();

        _component.inject(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_report_number);
    }

    /**
     * Creates the presenter
     * @return
     */
    @NonNull
    @Override
    public ReportPhoneNumberPresenter createPresenter() {
        return _component.presenter();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return false;
    }

    @Override
    public void onNewViewStateInstance() {

    }

    @Override
    public RestoreableViewState createViewState() {
        return new ReportPhoneNumberViewState();
    }

    /**
     * Shows the content view
     */
    @Override
    public void showContent() {

        ReportPhoneNumberViewState vs = (ReportPhoneNumberViewState) viewState;
        vs.setShowContent();

        //Clear the textboxes
        _editTextPhoneNumber.setText("");
        _editTextDescription.setText("");

        _btnReportPhoneNumber.setEnabled(true);
        _progressBar.setVisibility(View.GONE);

    }

    /**
     * Show the succeded event when the number is reported
     */
    @Override
    public void showSucceded() {

        ReportPhoneNumberViewState vs = (ReportPhoneNumberViewState) viewState;
        vs.setShowSucceded();

        //Clear the textboxes
        _editTextPhoneNumber.setText("");
        _editTextDescription.setText("");
        _editTextPhoneNumber.clearFocus();
        _editTextDescription.clearFocus();

        _btnReportPhoneNumber.setEnabled(true);
        _progressBar.setVisibility(View.GONE);

        View parentLayout = findViewById(R.id.contentView);

        AlertUtils.showSnackbar(parentLayout,getString(R.string.report_confirmation));

    }

    /**
     * Shows the Error view
     * @param error
     */
    @Override
    public void showError(String error) {
        ReportPhoneNumberViewState vs = (ReportPhoneNumberViewState) viewState;
        vs.setError(error);
        vs.setShowError();
        _btnReportPhoneNumber.setEnabled(true);
        _progressBar.setVisibility(View.GONE);
        AlertUtils.showToast(error, this);
    }

    /**
     * Shows the loading view
     */
    @Override
    public void showLoading() {
        ReportPhoneNumberViewState vs = (ReportPhoneNumberViewState) viewState;
        vs.setShowLoading();
        _btnReportPhoneNumber.setEnabled(false);
        _progressBar.setVisibility(View.VISIBLE);
        _progressBar.progressiveStart();
    }


    /**
     * Validate the fields of the submit form
     * @return
     */
    private boolean validateFields(){
        boolean valid = true;
        if(StringUtils.isEmpty(_editTextPhoneNumber.getText().toString())){
            valid = false;
            _editTextPhoneNumber.setError(getString(R.string.phone_number_required_text));
        }
        else
        {
            _editTextPhoneNumber.setError(null);
        }
        if(StringUtils.isEmpty(_editTextDescription.getText().toString())){
            valid = false;
            _editTextDescription.setError(getString(R.string.description_required_text));
        }
        else
        {
            _editTextDescription.setError(null);
        }
       return valid;
    }


    /**
     * User reporting
     */
    @OnClick(R.id.btn_report_phonenumber)
    public void btnReportPhoneNumberClicked(){
        if(validateFields()){
            _editTextPhoneNumber.clearFocus();
            _editTextDescription.clearFocus();
            presenter.reportPhoneNumber(_editTextPhoneNumber.getText().toString(),_editTextDescription.getText().toString());
        }
    }

     @OnClick(R.id.btnUseLastPhonecall)
    public void btnUseLastPhonecallClicked()
    {
        PhoneNumberLocalPersistence persistence =  new PhoneNumberLocalPersistence();
        this._editTextPhoneNumber.setText(persistence.getLastPhoneNumber(this));
    }

}
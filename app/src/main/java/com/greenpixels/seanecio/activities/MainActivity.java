package com.greenpixels.seanecio.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.greenpixels.seanecio.R;
import com.greenpixels.seanecio.components.AppComponent;
import com.greenpixels.seanecio.components.BlacklistedPhoneNumberListComponent;
import com.greenpixels.seanecio.components.DaggerBlacklistedPhoneNumberListComponent;
import com.greenpixels.seanecio.general_classes.MainApp;
import com.greenpixels.seanecio.modules.ContextProvider;
import com.greenpixels.seanecio.modules.FirebaseProvider;
import com.greenpixels.seanecio.modules.UtilsProvider;
import com.greenpixels.seanecio.presenters.BlacklistedPhoneNumberListPresenter;
import com.greenpixels.seanecio.view_states.ReportPhoneNumberViewState;
import com.greenpixels.seanecio.views.BlacklistedPhoneNumberListView;
import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.RestoreableViewState;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends MvpViewStateActivity<BlacklistedPhoneNumberListView,BlacklistedPhoneNumberListPresenter> implements BlacklistedPhoneNumberListView {

    @Bind(R.id.toolbar)
    Toolbar _toolbar;

    @Bind(R.id.fab)
    FloatingActionButton _fab;

    private BlacklistedPhoneNumberListComponent _component;

    /**
     * Inject the dependencies in the activity
     */
    @Override
    public void injectDependencies(){
        AppComponent appComponent = MainApp.get(this).appComponent();

        _component = DaggerBlacklistedPhoneNumberListComponent.builder()
                .appComponent(appComponent)
                .contextProvider(new ContextProvider(this))
                .utilsProvider(new UtilsProvider())
                .firebaseProvider(new FirebaseProvider())
                .build();

        _component.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(_toolbar);
    }


    /**
     * Creates the presenter
     * @return
     */
    @NonNull
    @Override
    public BlacklistedPhoneNumberListPresenter createPresenter() {
        return _component.presenter();
    }


    @Override
    public void onNewViewStateInstance() {

    }

    @Override
    public RestoreableViewState createViewState() {
        return new ReportPhoneNumberViewState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Shows the content view
     */
    @Override
    public void showContent() {

//        ReportPhoneNumberViewState vs = (ReportPhoneNumberViewState) viewState;
//        vs.setShowContent();
//
//        //Clear the textboxes
//        _editTextPhoneNumber.setText("");
//        _editTextDescription.setText("");
//
//        _btnReportPhoneNumber.setEnabled(true);
//        _progressBar.setVisibility(View.GONE);

    }

    @OnClick(R.id.fab)
    public void fabClicked(){

        Intent intent = new Intent(MainActivity.this, ReportPhoneNumberActivity.class);
        startActivity(intent);

    }


    /**
     * Shows the Error view
     * @param error
     */
    @Override
    public void showError(String error) {
//        ReportPhoneNumberViewState vs = (ReportPhoneNumberViewState) viewState;
//        vs.setError(error);
//        vs.setShowError();
//        _btnReportPhoneNumber.setEnabled(true);
//        _progressBar.setVisibility(View.GONE);
//        AlertUtils.showToast(error, this);
    }

    /**
     * Shows the loading view
     */
    @Override
    public void showLoading() {
//        ReportPhoneNumberViewState vs = (ReportPhoneNumberViewState) viewState;
//        vs.setShowLoading();
//        _btnReportPhoneNumber.setEnabled(false);
//        _progressBar.setVisibility(View.VISIBLE);
//        _progressBar.progressiveStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

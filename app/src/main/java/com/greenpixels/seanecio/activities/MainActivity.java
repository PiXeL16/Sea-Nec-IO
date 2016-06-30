package com.greenpixels.seanecio.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.greenpixels.seanecio.R;
import com.greenpixels.seanecio.adapters.BlacklistedPhoneNumberAdapter;
import com.greenpixels.seanecio.components.AppComponent;
import com.greenpixels.seanecio.components.BlacklistedPhoneNumberListComponent;
import com.greenpixels.seanecio.components.DaggerBlacklistedPhoneNumberListComponent;
import com.greenpixels.seanecio.general_classes.Constants;
import com.greenpixels.seanecio.general_classes.MainApp;
import com.greenpixels.seanecio.modules.FirebaseAnalyticsTrackerProvider;
import com.greenpixels.seanecio.modules.ContextProvider;
import com.greenpixels.seanecio.modules.FirebaseProvider;
import com.greenpixels.seanecio.modules.UtilsProvider;
import com.greenpixels.seanecio.presenters.BlacklistedPhoneNumberListPresenter;
import com.greenpixels.seanecio.view_states.BlacklistedPhoneNumberListViewState;
import com.greenpixels.seanecio.views.BlacklistedPhoneNumberListView;
import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.RestoreableViewState;

import butterknife.Bind;
import butterknife.OnClick;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

/**
 * Main activity class that will contain a list of blacklisted phone numbers
 */
public class MainActivity extends MvpViewStateActivity<BlacklistedPhoneNumberListView,BlacklistedPhoneNumberListPresenter> implements BlacklistedPhoneNumberListView {

    @Bind(R.id.toolbar)
    Toolbar _toolbar;

    @Bind(R.id.fab)
    FloatingActionButton _fab;

    @Bind(R.id.recyclerView)
    RecyclerView _recyclerView;

    @Bind(R.id.progressBar)
    SmoothProgressBar _progressBar;

    private BlacklistedPhoneNumberListComponent _component;

    private BlacklistedPhoneNumberAdapter _adapter;

    private RecyclerView.AdapterDataObserver _observer;

    private FirebaseAnalytics _firebaseAnalytics;

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
                .firebaseAnalyticsTrackerProvider(new FirebaseAnalyticsTrackerProvider(this))
                .build();

        _component.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(_toolbar);

        _adapter = _component.adapter();

        _recyclerView.setAdapter(_adapter);

        _firebaseAnalytics = _component.firebaseAnalytics();

        _recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Shows loading and when the adapters starts to show some content hide the loading
        this.showLoading();
        this._observer = new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                showContent();
                _adapter.unregisterAdapterDataObserver(_observer);
            }
        };

        _adapter.registerAdapterDataObserver(_observer);
    }


    /**
     *Check for permissions, will show dialog on api 23: TODO SHOW funcionality explaning why it needs that permission
     */
    public void checkForPhonePermissionsAndRequest()
    {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        0);
        }
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
    protected void onResume() {
        super.onResume();

        //Check for permisions on API 23(M)
        checkForPhonePermissionsAndRequest();
    }


    @Override
    public RestoreableViewState createViewState() {
        return new BlacklistedPhoneNumberListViewState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void showContent() {

        _progressBar.setVisibility(View.GONE);
        _progressBar.progressiveStop();
    }

    @OnClick(R.id.fab)
    public void fabClicked(){

        Intent intent = new Intent(MainActivity.this, ReportPhoneNumberActivity.class);
        startActivity(intent);

    }


    /**
     * Shows the Error view not really used at the momment
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
     * Shows the loading view, not really used at the momment since its done with the firebase adapter
     */
    @Override
    public void showLoading() {
//        BlacklistedPhoneNumberListViewState vs = (BlacklistedPhoneNumberListViewState) viewState;
//        vs.setShowLoading();
        _progressBar.setVisibility(View.VISIBLE);
        _progressBar.progressiveStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {

            Intent aboutIntent =  new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.ABOUT_SITE));
            this.startActivity(aboutIntent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

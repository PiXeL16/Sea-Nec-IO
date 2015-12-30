package com.greenpixels.seanecio.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.Firebase;
import com.greenpixels.seanecio.R;
import com.greenpixels.seanecio.adapters.BlacklistedPhoneNumberAdapter;
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

    private BlacklistedPhoneNumberListComponent _component;

    private BlacklistedPhoneNumberAdapter _adapter;

    private Firebase _firebase;

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

        _adapter = _component.adapter();

        _recyclerView.setAdapter(_adapter);

        _recyclerView.setLayoutManager(new LinearLayoutManager(this));


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

    /**
     * Creates the view state
     * @return
     */
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
     * Shows the content view. Not really used at the momment
     */
    @Override
    public void showContent() {

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

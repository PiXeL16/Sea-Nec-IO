package com.greenpixels.seanecio.presenters;

import com.firebase.client.Firebase;
import com.greenpixels.seanecio.model.PhoneNumber;
import com.greenpixels.seanecio.views.ReportPhoneNumberView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by chris on 12/15/15.
 */
public class ReportPhoneNumberPresenter extends MvpBasePresenter<ReportPhoneNumberView> {

    private EventBus _eventBus;
    private Firebase _firebase;

    @Inject
    public ReportPhoneNumberPresenter(EventBus eventBus, Firebase firebase) {
        _eventBus = eventBus;
        _firebase = firebase;
    }

    @Override
    public void attachView(ReportPhoneNumberView reportView){
        super.attachView(reportView);
        _eventBus.register(this);
    }

    @Override
    public void detachView(boolean retainInstance){
        super.detachView(retainInstance);
        if(!retainInstance){
            _eventBus.unregister(this);
        }
    }

    public void reportPhoneNumber(String phoneNumber, String description){
        if(isViewAttached()) {
            getView().showLoading();
        }
        Firebase phoneNumbers = _firebase.child(PhoneNumber.collectionName);
        PhoneNumber phoneNumberObject = new PhoneNumber(phoneNumber,description);

        phoneNumbers.push().setValue(phoneNumberObject);
        getView().showContent();
    }

//    public void onEvent(LoginSuccessEvent event){
//        getView().loginSuccessful(event.getUser());
//    }
//
//    public void onEvent(LoginErrorEvent event){
//        getView().showError(event.getRestMessage());
//    }
//
//    public void onEvent(AlertDialogPushEvent event){
//        getView().showDialogPush(event.getTitle(), event.getAlert());
//    }

}

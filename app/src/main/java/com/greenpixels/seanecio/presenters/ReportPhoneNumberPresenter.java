package com.greenpixels.seanecio.presenters;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.greenpixels.seanecio.model.PhoneNumber;
import com.greenpixels.seanecio.views.ReportPhoneNumberView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Presenter class for the Report Phone Number
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
//        _eventBus.register(this);
    }

    @Override
    public void detachView(boolean retainInstance){
        super.detachView(retainInstance);
        if(!retainInstance){
//            _eventBus.unregister(this);
        }
    }

//    public void onEvent(){
//    }

    /**
     * Adds a reported phone number to firebase
     * This will change
     * @param phoneNumber
     * @param description
     */
    public void reportPhoneNumber(String phoneNumber, String description){
        if(isViewAttached()) {
            getView().showLoading();
        }
        Firebase phoneNumbers = _firebase.child(PhoneNumber.collectionName);
        PhoneNumber phoneNumberObject = new PhoneNumber(phoneNumber,description);

        phoneNumbers.push().setValue(phoneNumberObject, new Firebase.CompletionListener() {

            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                   getView().showError(firebaseError.getMessage());
                } else {
                    getView().showContent();
                }
            }
        });
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

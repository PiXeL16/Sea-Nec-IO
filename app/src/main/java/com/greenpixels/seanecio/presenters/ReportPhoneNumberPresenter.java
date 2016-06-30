package com.greenpixels.seanecio.presenters;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.greenpixels.seanecio.general_classes.Constants;
import com.greenpixels.seanecio.model.PhoneNumber;
import com.greenpixels.seanecio.telephony.PhoneNumberUtils;
import com.greenpixels.seanecio.views.ReportPhoneNumberView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Presenter class for the Report Phone Number
 */
public class ReportPhoneNumberPresenter extends MvpBasePresenter<ReportPhoneNumberView> {

    private EventBus _eventBus;
    private FirebaseDatabase _firebase;
    private DatabaseReference _databaseReference;

    @Inject
    public ReportPhoneNumberPresenter(EventBus eventBus, FirebaseDatabase firebase) {
        _eventBus = eventBus;
        _firebase = firebase;
        _databaseReference = _firebase.getReference();
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
        //Shows the loading indicator if the view is visible
        if(isViewAttached()) {
            getView().showLoading();
        }
        //Gets the firebase collection to insert
        DatabaseReference phoneNumbers = _databaseReference.child(PhoneNumber.collectionName);

        //Strips the phone number of symbols and country code
        String stripedPhoneNumber = PhoneNumberUtils.stripCountryCodeFromPhoneNumber(phoneNumber, Constants.getDefaultCountryCode());

        //Creates the phoneNumberObject to insert
        PhoneNumber phoneNumberObject = new PhoneNumber(stripedPhoneNumber,description);

        phoneNumbers.push().setValue(phoneNumberObject, new DatabaseReference.CompletionListener() {

            @Override
            public void onComplete(DatabaseError firebaseError, DatabaseReference firebase) {
                if (firebaseError != null) {
                    //Shows an error message
                   getView().showError(firebaseError.getMessage());
                } else {
                    //Show the succeded message
                    getView().showSucceded();
                }
            }
        });
    }
}

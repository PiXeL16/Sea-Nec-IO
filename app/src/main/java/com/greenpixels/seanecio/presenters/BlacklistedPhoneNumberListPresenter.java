package com.greenpixels.seanecio.presenters;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.greenpixels.seanecio.model.BlacklistedPhoneNumber;
import com.greenpixels.seanecio.model.PhoneNumber;
import com.greenpixels.seanecio.views.BlacklistedPhoneNumberListView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import timber.log.Timber;

/**
 * Presenter for the blacklisted phone number activity (main activity)
 */
public class BlacklistedPhoneNumberListPresenter extends MvpBasePresenter<BlacklistedPhoneNumberListView> {

    private EventBus _eventBus;
    private Firebase _firebase;

    @Inject
    public BlacklistedPhoneNumberListPresenter(EventBus eventBus, Firebase firebase) {
        _eventBus = eventBus;
        _firebase = firebase;
    }

    @Override
    public void attachView(BlacklistedPhoneNumberListView reportView){
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

    /**
     * Loads the blacklisted phone number from the database
     */
    public void loadBlacklistedPhoneNumbers()
    {

        if(isViewAttached())
        {
            getView().showLoading();
        }

        Firebase blacklistedPhoneNumbers = _firebase.child(BlacklistedPhoneNumber.collectionName);


        blacklistedPhoneNumbers .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Timber.d("There are " + snapshot.getChildrenCount() + " blacklisted phone numbers");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    BlacklistedPhoneNumber blacklistedPhoneNumbers = postSnapshot.getValue(BlacklistedPhoneNumber.class);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Timber.e("The read failed: " + firebaseError.getMessage());
            }
        });
    }

//    public void onEvent(){
//    }


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
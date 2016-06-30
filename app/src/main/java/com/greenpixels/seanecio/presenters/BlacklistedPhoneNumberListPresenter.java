package com.greenpixels.seanecio.presenters;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.greenpixels.seanecio.model.BlacklistedPhoneNumber;
import com.greenpixels.seanecio.views.BlacklistedPhoneNumberListView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import timber.log.Timber;

/**
 * Presenter for the blacklisted phone number activity (main activity)
 * Not used since its done with the firebase adapter
 */
public class BlacklistedPhoneNumberListPresenter extends MvpBasePresenter<BlacklistedPhoneNumberListView> {

    private EventBus _eventBus;
    private FirebaseDatabase _firebase;
    private DatabaseReference _databaseReference;

    @Inject
    public BlacklistedPhoneNumberListPresenter(EventBus eventBus, FirebaseDatabase firebase) {
        _eventBus = eventBus;
        _firebase = firebase;
        _databaseReference = _firebase.getReference();
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

        DatabaseReference blacklistedPhoneNumbers = _databaseReference.child(BlacklistedPhoneNumber.collectionName);

        blacklistedPhoneNumbers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Timber.d("There are " + snapshot.getChildrenCount() + " blacklisted phone numbers");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    BlacklistedPhoneNumber blacklistedPhoneNumbers = postSnapshot.getValue(BlacklistedPhoneNumber.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
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
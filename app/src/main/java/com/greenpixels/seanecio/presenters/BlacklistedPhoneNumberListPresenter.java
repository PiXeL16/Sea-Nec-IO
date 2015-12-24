package com.greenpixels.seanecio.presenters;

import com.firebase.client.Firebase;
import com.greenpixels.seanecio.views.BlacklistedPhoneNumberListView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

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
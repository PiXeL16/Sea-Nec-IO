package com.greenpixels.seanecio.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import rx.android.internal.Preconditions;

/**
 * Created by chris on 12/15/15.
 * Provides the context object
 */
@Module
public class ContextProvider {
    private final Context _context;

    public ContextProvider(Context context) {
        _context = Preconditions.checkNotNull(context, "context must not be null");
    }

    @Provides
    public Context provideActivityContext(){
        return _context;
    }
}

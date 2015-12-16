package com.greenpixels.seanecio.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import rx.android.internal.Preconditions;

/**
 * Dagger2 Provides the context object
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

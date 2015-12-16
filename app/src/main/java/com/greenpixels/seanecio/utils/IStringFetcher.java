package com.greenpixels.seanecio.utils;

/**
 * Created by chris on 6/29/15.
 */

public interface IStringFetcher {

    public String getString(int id);

    public String getString(int id, Object... format);

}
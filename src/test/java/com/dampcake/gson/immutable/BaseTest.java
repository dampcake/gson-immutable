package com.dampcake.gson.immutable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class BaseTest {

    protected final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(ImmutableAdapterFactory.forGuava())
            .registerTypeAdapterFactory(ImmutableAdapterFactory.forJava())
            .create();
}

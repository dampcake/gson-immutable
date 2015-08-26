package com.dampcake.gson.immutable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public abstract class BaseTest {

    protected final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(ImmutableAdapterFactory.forGuava())
            .registerTypeAdapterFactory(ImmutableAdapterFactory.forJava())
            .create();

    @Rule
    public ExpectedException exception = ExpectedException.none();
}

package com.dampcake.gson.immutable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public abstract class BaseTest {

    protected final Gson gson;

    public BaseTest() {
        GsonBuilder builder = new GsonBuilder();
        ImmutableAdapterFactory.registerOn(builder, true);
        gson = builder.create();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();
}

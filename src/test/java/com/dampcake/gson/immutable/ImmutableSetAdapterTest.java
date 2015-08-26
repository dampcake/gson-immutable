package com.dampcake.gson.immutable;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.Set;

import static com.dampcake.gson.immutable.TestData.LIST_VALUES;
import static com.dampcake.gson.immutable.TestTypes.I_SET_TYPE;
import static com.dampcake.gson.immutable.TestTypes.SET_TYPE;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

public class ImmutableSetAdapterTest extends BaseTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testGuava() {
        String json = gson.toJson(LIST_VALUES);
        ImmutableSet<String> set = gson.fromJson(json, I_SET_TYPE.getType());

        assertThat(set, hasItems(LIST_VALUES.toArray(new String[3])));
        assertNotSame(LIST_VALUES, set);

        set.add("test");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testInterface() {
        String json = gson.toJson(LIST_VALUES);
        Set<String> set = gson.fromJson(json, SET_TYPE.getType());

        assertThat(set, hasItems(LIST_VALUES.toArray(new String[3])));
        assertNotSame(LIST_VALUES, set);

        set.add("test");
    }
}

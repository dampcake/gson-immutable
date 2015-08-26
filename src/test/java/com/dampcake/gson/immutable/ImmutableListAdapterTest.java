package com.dampcake.gson.immutable;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static com.dampcake.gson.immutable.TestData.LIST_VALUES;
import static com.dampcake.gson.immutable.TestTypes.I_LIST_TYPE;
import static com.dampcake.gson.immutable.TestTypes.LIST_TYPE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class ImmutableListAdapterTest extends BaseTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testGuava() {
        String json = gson.toJson(LIST_VALUES);
        ImmutableList<String> list = gson.fromJson(json, I_LIST_TYPE.getType());

        assertEquals(LIST_VALUES, list);
        assertNotSame(LIST_VALUES, list);

        list.add("test");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testInterface() {
        String json = gson.toJson(LIST_VALUES);
        List<String> list = gson.fromJson(json, LIST_TYPE.getType());

        assertEquals(LIST_VALUES, list);
        assertNotSame(LIST_VALUES, list);

        list.add("test");
    }
}

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

    @Test(expected = NullPointerException.class)
    public void testConstructorNull() {
        new ImmutableListAdapter(null);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testGuava() {
        ImmutableList<String> list = gson.fromJson(gson.toJson(LIST_VALUES), I_LIST_TYPE.getType());

        assertEquals(LIST_VALUES, list);
        assertNotSame(LIST_VALUES, list);

        exception.expect(UnsupportedOperationException.class);
        list.add("test");
    }

    @Test
    public void testInterface() {
        List<String> list = gson.fromJson(gson.toJson(LIST_VALUES), LIST_TYPE.getType());

        assertEquals(LIST_VALUES, list);
        assertNotSame(LIST_VALUES, list);

        exception.expect(UnsupportedOperationException.class);
        list.add("test");
    }
}

package com.dampcake.gson.immutable;

import com.google.common.collect.ImmutableSortedSet;
import org.junit.Test;

import java.util.SortedSet;

import static com.dampcake.gson.immutable.TestData.LIST_VALUES;
import static com.dampcake.gson.immutable.TestTypes.I_SSET_TYPE;
import static com.dampcake.gson.immutable.TestTypes.SSET_TYPE;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

public class ImmutableSortedSetAdapterTest extends BaseTest {

    @Test(expected = NullPointerException.class)
    public void testConstructorNull() {
        new ImmutableSortedSetAdapter(null);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testGuava() {
        String json = gson.toJson(LIST_VALUES);
        ImmutableSortedSet<String> set = gson.fromJson(json, I_SSET_TYPE.getType());

        assertThat(set, hasItems(LIST_VALUES.toArray(new String[3])));
        assertNotSame(LIST_VALUES, set);

        exception.expect(UnsupportedOperationException.class);
        set.add("test");
    }

    @Test
    public void testInterface() {
        String json = gson.toJson(LIST_VALUES);
        SortedSet<String> set = gson.fromJson(json, SSET_TYPE.getType());

        assertThat(set, hasItems(LIST_VALUES.toArray(new String[3])));
        assertNotSame(LIST_VALUES, set);

        exception.expect(UnsupportedOperationException.class);
        set.add("test");
    }
}

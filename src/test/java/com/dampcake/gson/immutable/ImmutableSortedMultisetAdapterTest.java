package com.dampcake.gson.immutable;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import org.junit.Test;

import static com.dampcake.gson.immutable.TestData.LIST_VALUES;
import static com.dampcake.gson.immutable.TestData.MSET_VALUES;
import static com.dampcake.gson.immutable.TestTypes.I_MSET_TYPE;
import static com.dampcake.gson.immutable.TestTypes.MSET_TYPE;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

public class ImmutableSortedMultisetAdapterTest extends BaseTest {

    @Test(expected = NullPointerException.class)
    public void testConstructorNull() {
        new ImmutableSetAdapter(null);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testGuava() {
        ImmutableMultiset<String> set = gson.fromJson(gson.toJson(MSET_VALUES), I_MSET_TYPE.getType());

        assertThat(set, hasItems(MSET_VALUES.toArray(new String[4])));
        assertThat(set.count("val1"), is(2));
        assertNotSame(LIST_VALUES, set);

        exception.expect(UnsupportedOperationException.class);
        set.add("test");
    }

    @Test
    public void testInterface() {
        Multiset<String> set = gson.fromJson(gson.toJson(MSET_VALUES), MSET_TYPE.getType());

        assertThat(set, hasItems(MSET_VALUES.toArray(new String[4])));
        assertThat(set.count("val1"), is(2));
        assertNotSame(LIST_VALUES, set);

        exception.expect(UnsupportedOperationException.class);
        set.add("test");
    }
}

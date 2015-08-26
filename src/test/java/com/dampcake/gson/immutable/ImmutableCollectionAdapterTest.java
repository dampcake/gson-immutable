package com.dampcake.gson.immutable;

import com.google.common.collect.ImmutableCollection;
import org.junit.Test;

import java.util.Collection;

import static com.dampcake.gson.immutable.TestData.LIST_VALUES;
import static com.dampcake.gson.immutable.TestTypes.COLLECTION_TYPE;
import static com.dampcake.gson.immutable.TestTypes.I_COLLECTION_TYPE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class ImmutableCollectionAdapterTest extends BaseTest {

    @Test(expected = NullPointerException.class)
    public void testConstructorNull() {
        new ImmutableCollectionAdapter(null);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testGuava() {
        ImmutableCollection<String> collection = gson.fromJson(gson.toJson(LIST_VALUES), I_COLLECTION_TYPE.getType());

        assertEquals(LIST_VALUES, collection);
        assertNotSame(LIST_VALUES, collection);

        exception.expect(UnsupportedOperationException.class);
        collection.add("test");
    }

    @Test
    public void testInterface() {
        Collection<String> collection = gson.fromJson(gson.toJson(LIST_VALUES), COLLECTION_TYPE.getType());

        assertEquals(LIST_VALUES, collection);
        assertNotSame(LIST_VALUES, collection);

        exception.expect(UnsupportedOperationException.class);
        collection.add("test");
    }
}

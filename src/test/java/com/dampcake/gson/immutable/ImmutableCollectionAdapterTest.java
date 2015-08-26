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

    @Test(expected = UnsupportedOperationException.class)
    public void testGuava() {
        String json = gson.toJson(LIST_VALUES);
        ImmutableCollection<String> collection = gson.fromJson(json, I_COLLECTION_TYPE.getType());

        assertEquals(LIST_VALUES, collection);
        assertNotSame(LIST_VALUES, collection);

        collection.add("test");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testInterface() {
        String json = gson.toJson(LIST_VALUES);
        Collection<String> collection = gson.fromJson(json, COLLECTION_TYPE.getType());

        assertEquals(LIST_VALUES, collection);
        assertNotSame(LIST_VALUES, collection);

        collection.add("test");
    }
}

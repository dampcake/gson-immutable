package com.dampcake.gson.immutable;

import com.google.common.collect.ImmutableSortedMap;
import org.junit.Test;

import java.util.SortedMap;

import static com.dampcake.gson.immutable.TestData.MAP_VALUES;
import static com.dampcake.gson.immutable.TestTypes.I_SMAP_TYPE;
import static com.dampcake.gson.immutable.TestTypes.SMAP_TYPE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class ImmutableSortedMapAdapterTest extends BaseTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testGuava() {
        String json = gson.toJson(MAP_VALUES);
        ImmutableSortedMap<String, String> map = gson.fromJson(json, I_SMAP_TYPE.getType());

        assertEquals(MAP_VALUES, map);
        assertNotSame(MAP_VALUES, map);

        map.put("test", "test");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testInterface() {
        String json = gson.toJson(MAP_VALUES);
        SortedMap<String, String> map = gson.fromJson(json, SMAP_TYPE.getType());

        assertEquals(MAP_VALUES, map);
        assertNotSame(MAP_VALUES, map);

        map.put("test", "test");
    }
}

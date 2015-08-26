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

    @Test(expected = NullPointerException.class)
    public void testConstructorNull() {
        new ImmutableSortedMapAdapter(null);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testGuava() {
        ImmutableSortedMap<String, String> map = gson.fromJson(gson.toJson(MAP_VALUES), I_SMAP_TYPE.getType());

        assertEquals(MAP_VALUES, map);
        assertNotSame(MAP_VALUES, map);

        exception.expect(UnsupportedOperationException.class);
        map.put("test", "test");
    }

    @Test
    public void testInterface() {
        SortedMap<String, String> map = gson.fromJson(gson.toJson(MAP_VALUES), SMAP_TYPE.getType());

        assertEquals(MAP_VALUES, map);
        assertNotSame(MAP_VALUES, map);

        exception.expect(UnsupportedOperationException.class);
        map.put("test", "test");
    }
}

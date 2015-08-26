package com.dampcake.gson.immutable;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;

import static com.dampcake.gson.immutable.TestData.MAP_VALUES;
import static com.dampcake.gson.immutable.TestTypes.I_MAP_TYPE;
import static com.dampcake.gson.immutable.TestTypes.MAP_TYPE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class ImmutableMapAdapterTest extends BaseTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testGuava() {
        String json = gson.toJson(MAP_VALUES);
        ImmutableMap<String, String> map = gson.fromJson(json, I_MAP_TYPE.getType());

        assertEquals(MAP_VALUES, map);
        assertNotSame(MAP_VALUES, map);

        map.put("test", "test");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testInterface() {
        String json = gson.toJson(MAP_VALUES);
        Map<String, String> map = gson.fromJson(json, MAP_TYPE.getType());

        assertEquals(MAP_VALUES, map);
        assertNotSame(MAP_VALUES, map);

        map.put("test", "test");
    }
}

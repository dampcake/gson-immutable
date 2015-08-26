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

    @Test(expected = NullPointerException.class)
    public void testConstructorNull() {
        new ImmutableMapAdapter(null);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testGuava() {
        ImmutableMap<String, String> map = gson.fromJson(gson.toJson(MAP_VALUES), I_MAP_TYPE.getType());

        assertEquals(MAP_VALUES, map);
        assertNotSame(MAP_VALUES, map);

        exception.expect(UnsupportedOperationException.class);
        map.put("test", "test");
    }

    @Test
    public void testInterface() {
        Map<String, String> map = gson.fromJson(gson.toJson(MAP_VALUES), MAP_TYPE.getType());

        assertEquals(MAP_VALUES, map);
        assertNotSame(MAP_VALUES, map);

        exception.expect(UnsupportedOperationException.class);
        map.put("test", "test");
    }
}

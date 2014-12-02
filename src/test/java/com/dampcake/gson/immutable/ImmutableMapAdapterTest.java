package com.dampcake.gson.immutable;

import static com.dampcake.gson.immutable.TestData.*;
import static com.dampcake.gson.immutable.TestTypes.*;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

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

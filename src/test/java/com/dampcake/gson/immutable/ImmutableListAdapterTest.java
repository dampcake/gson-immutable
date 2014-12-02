package com.dampcake.gson.immutable;

import static com.dampcake.gson.immutable.TestData.*;
import static com.dampcake.gson.immutable.TestTypes.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class ImmutableListAdapterTest extends BaseTest {
	
	@Test(expected = UnsupportedOperationException.class)
	public void testGuava() {
		String json = gson.toJson(LIST_VALUES);
		ImmutableList<String> list = gson.fromJson(json, I_LIST_TYPE.getType());
		
		assertEquals(LIST_VALUES, list);
		assertNotSame(LIST_VALUES, list);
		
		list.add("test");
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testInterface() {
		String json = gson.toJson(LIST_VALUES);
		List<String> list = gson.fromJson(json, LIST_TYPE.getType());
		
		assertEquals(LIST_VALUES, list);
		assertNotSame(LIST_VALUES, list);
		
		list.add("test");
	}
}

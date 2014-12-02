package com.dampcake.gson.immutable;

import static com.dampcake.gson.immutable.TestData.*;
import static com.dampcake.gson.immutable.TestTypes.*;
import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.google.common.collect.ImmutableCollection;

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

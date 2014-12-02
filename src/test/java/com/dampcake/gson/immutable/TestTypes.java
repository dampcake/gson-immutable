package com.dampcake.gson.immutable;

import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.gson.reflect.TypeToken;

public final class TestTypes {
	
	public static final TypeToken<ImmutableList<String>> I_LIST_TYPE = new TypeToken<ImmutableList<String>>(){};
	public static final TypeToken<List<String>> LIST_TYPE = new TypeToken<List<String>>(){};
	
	public static final TypeToken<ImmutableMap<String, String>> I_MAP_TYPE = new TypeToken<ImmutableMap<String, String>>(){};
	public static final TypeToken<ImmutableMap<String, String>> MAP_TYPE = new TypeToken<ImmutableMap<String, String>>(){};
	
	public static final TypeToken<ImmutableSet<String>> I_SET_TYPE = new TypeToken<ImmutableSet<String>>(){};
	public static final TypeToken<Set<String>> SET_TYPE = new TypeToken<Set<String>>(){};
	
	public static final TypeToken<ImmutableSortedMap<String, String>> I_SMAP_TYPE = new TypeToken<ImmutableSortedMap<String, String>>(){};
	public static final TypeToken<SortedMap<String, String>> SMAP_TYPE = new TypeToken<SortedMap<String, String>>(){};
	
	public static final TypeToken<ImmutableSortedSet<String>> I_SSET_TYPE = new TypeToken<ImmutableSortedSet<String>>(){};
	public static final TypeToken<SortedSet<String>> SSET_TYPE = new TypeToken<SortedSet<String>>(){};
	
	
	private TestTypes() {
	}
}

package com.dampcake.gson.immutable;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.gson.reflect.TypeToken;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public final class TestTypes {

    public static final TypeToken<ImmutableCollection<String>> I_COLLECTION_TYPE = new TypeToken<ImmutableCollection<String>>() {
    };
    public static final TypeToken<Collection<String>> COLLECTION_TYPE = new TypeToken<Collection<String>>() {
    };

    public static final TypeToken<ImmutableList<String>> I_LIST_TYPE = new TypeToken<ImmutableList<String>>() {
    };
    public static final TypeToken<List<String>> LIST_TYPE = new TypeToken<List<String>>() {
    };

    public static final TypeToken<ImmutableMap<String, String>> I_MAP_TYPE = new TypeToken<ImmutableMap<String, String>>() {
    };
    public static final TypeToken<Map<String, String>> MAP_TYPE = new TypeToken<Map<String, String>>() {
    };

    public static final TypeToken<ImmutableSet<String>> I_SET_TYPE = new TypeToken<ImmutableSet<String>>() {
    };
    public static final TypeToken<Set<String>> SET_TYPE = new TypeToken<Set<String>>() {
    };

    public static final TypeToken<ImmutableSortedMap<String, String>> I_SMAP_TYPE = new TypeToken<ImmutableSortedMap<String, String>>() {
    };
    public static final TypeToken<SortedMap<String, String>> SMAP_TYPE = new TypeToken<SortedMap<String, String>>() {
    };

    public static final TypeToken<ImmutableSortedSet<String>> I_SSET_TYPE = new TypeToken<ImmutableSortedSet<String>>() {
    };
    public static final TypeToken<SortedSet<String>> SSET_TYPE = new TypeToken<SortedSet<String>>() {
    };
    public static final TypeToken<NavigableSet<String>> NSET_TYPE = new TypeToken<NavigableSet<String>>() {
    };

    private TestTypes() {
    }
}

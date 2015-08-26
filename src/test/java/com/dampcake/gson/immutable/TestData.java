package com.dampcake.gson.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

public final class TestData {

    public static final List<String> LIST_VALUES = ImmutableList.<String>builder()
            .add("val1")
            .add("val2")
            .add("testa")
            .build();

    public static final Map<String, String> MAP_VALUES = ImmutableMap.<String, String>builder()
            .put("key1", "val1")
            .put("key2", "val2")
            .put("test", "tv")
            .build();

    private TestData() {
    }
}

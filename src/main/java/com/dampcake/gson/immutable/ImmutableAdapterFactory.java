/*
 * Copyright 2016 Adam Peck.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dampcake.gson.immutable;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedMultiset;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Multiset;
import com.google.common.collect.SortedMultiset;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * {@link TypeAdapterFactory} for de-serializing Gson Immutable types.
 *
 * @author Adam Peck
 */
public final class ImmutableAdapterFactory implements TypeAdapterFactory {

    private static final Map<Class, Class> interfaceMap = ImmutableMap.<Class, Class>builder()
            .put(ImmutableCollection.class, Collection.class)
            .put(ImmutableList.class, List.class)
            .put(ImmutableSet.class, Set.class)
            .put(ImmutableSortedSet.class, SortedSet.class)
            .put(ImmutableMap.class, Map.class)
            .put(ImmutableSortedMap.class, SortedMap.class)
            .put(ImmutableMultiset.class, Multiset.class)
            .put(ImmutableSortedMultiset.class, SortedMultiset.class)
            .build();

    private static final Map<Class, InstanceCreator<?>> creatorMap = ImmutableMap.<Class, InstanceCreator<?>>builder()
            .put(Multiset.class, new MultisetCreator())
            .put(SortedMultiset.class, new SortedMultisetCreator())
            .build();

    private final Map<Class, Class<? extends TypeAdapter>> adapters;

    ImmutableAdapterFactory(Map<Class, Class<? extends TypeAdapter>> adapters) {
        this.adapters = adapters;
    }

    private static TypeAdapterFactory forGuava() {
        return new ImmutableAdapterFactory(ImmutableMap.<Class, Class<? extends TypeAdapter>>builder()
                .put(ImmutableCollection.class, ImmutableCollectionAdapter.class)
                .put(ImmutableList.class, ImmutableListAdapter.class)
                .put(ImmutableSet.class, ImmutableSetAdapter.class)
                .put(ImmutableSortedSet.class, ImmutableSortedSetAdapter.class)
                .put(ImmutableMap.class, ImmutableMapAdapter.class)
                .put(ImmutableSortedMap.class, ImmutableSortedMapAdapter.class)
                .put(ImmutableMultiset.class, ImmutableMultisetAdapter.class)
                .put(ImmutableSortedMultiset.class, ImmutableSortedMultisetAdapter.class)
                .build());
    }

    private static TypeAdapterFactory forJava() {
        return new ImmutableAdapterFactory(ImmutableMap.<Class, Class<? extends TypeAdapter>>builder()
                .put(Collection.class, ImmutableCollectionAdapter.class)
                .put(List.class, ImmutableListAdapter.class)
                .put(Set.class, ImmutableSetAdapter.class)
                .put(SortedSet.class, ImmutableSortedSetAdapter.class)
                .put(NavigableSet.class, ImmutableSortedSetAdapter.class)
                .put(Map.class, ImmutableMapAdapter.class)
                .put(SortedMap.class, ImmutableSortedMapAdapter.class)
                .put(Multiset.class, ImmutableMultisetAdapter.class)
                .put(SortedMultiset.class, ImmutableSortedMultisetAdapter.class)
                .build());
    }

    /**
     * Register the ImmutableAdapterFactory in the passed GsonBuilder.
     * Same as calling <code>ImmutableAdapterFactory.registerOn(builder, false);</code>
     *
     *
     * @param builder the GsonBuilder to register on
     * @see ImmutableAdapterFactory#registerOn(GsonBuilder, boolean)
     */
    public static void registerOn(GsonBuilder builder) {
        registerOn(builder, false);
    }

    /**
     * Register the ImmutableAdapterFactory in the passed GsonBuilder.
     * If interfaces is set to true interface types such as List, Collection, Multiset, etc, will be registered
     * and will return their Immutable type instead.
     *
     * @param builder the GsonBuilder to register on
     * @param interfaces true to register interfaces
     */
    public static void registerOn(GsonBuilder builder, boolean interfaces) {
        checkNotNull(builder, "GsonBuilder cannot be null");
        builder.registerTypeAdapterFactory(forGuava());

        if (interfaces)
            builder.registerTypeAdapterFactory(forJava());

        for (Map.Entry<Class, InstanceCreator<?>> e : creatorMap.entrySet()) {
            builder.registerTypeAdapter(e.getKey(), e.getValue());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (adapters.containsKey(type.getRawType())) {
            TypeAdapter delegate = getDelegate(gson, type);
            try {
                return adapters.get(type.getRawType()).getConstructor(TypeAdapter.class).newInstance(delegate);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private <T> TypeAdapter getDelegate(Gson gson, final TypeToken<T> type) {
        final Class<?> iface = type.getRawType();

        if (!iface.isInterface()) {
            checkState(type.getType() instanceof ParameterizedType, "Non-mappable type found");

            TypeToken<?> t = TypeToken.get(new ParameterizedType() {
                @Override
                public Type[] getActualTypeArguments() {
                    return ((ParameterizedType) type.getType()).getActualTypeArguments();
                }

                @Override
                public Type getRawType() {
                    return interfaceMap.get(iface);
                }

                @Override
                public Type getOwnerType() {
                    return ((ParameterizedType) type.getType()).getOwnerType();
                }
            });
            return gson.getDelegateAdapter(this, t);
        }

        return gson.getDelegateAdapter(this, type);
    }
}

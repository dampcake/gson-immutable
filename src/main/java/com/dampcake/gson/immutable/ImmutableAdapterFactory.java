/*
 * Copyright 2014 Adam Peck.
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
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.gson.Gson;
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
            .build();

    private final Map<Class, Class<? extends TypeAdapter>> adapters;

    ImmutableAdapterFactory(Map<Class, Class<? extends TypeAdapter>> adapters) {
        this.adapters = adapters;
    }

    /**
     * Creates a {@link TypeAdapterFactory} for de-serializing Immutable types specified
     * by Guava interfaces.
     *
     * @return the created {@link TypeAdapterFactory}.
     */
    public static TypeAdapterFactory forGuava() {
        return new ImmutableAdapterFactory(ImmutableMap.<Class, Class<? extends TypeAdapter>>builder()
                .put(ImmutableCollection.class, ImmutableCollectionAdapter.class)
                .put(ImmutableList.class, ImmutableListAdapter.class)
                .put(ImmutableSet.class, ImmutableSetAdapter.class)
                .put(ImmutableSortedSet.class, ImmutableSortedSetAdapter.class)
                .put(ImmutableMap.class, ImmutableMapAdapter.class)
                .put(ImmutableSortedMap.class, ImmutableSortedMapAdapter.class)
                .build());
    }

    /**
     * Creates a {@link TypeAdapterFactory} for de-serializing Immutable types specified
     * by Java interfaces.
     *
     * @return the created {@link TypeAdapterFactory}.
     */
    public static TypeAdapterFactory forJava() {
        return new ImmutableAdapterFactory(ImmutableMap.<Class, Class<? extends TypeAdapter>>builder()
                .put(Collection.class, ImmutableCollectionAdapter.class)
                .put(List.class, ImmutableListAdapter.class)
                .put(Set.class, ImmutableSetAdapter.class)
                .put(SortedSet.class, ImmutableSortedSetAdapter.class)
                .put(NavigableSet.class, ImmutableSortedSetAdapter.class)
                .put(Map.class, ImmutableMapAdapter.class)
                .put(SortedMap.class, ImmutableSortedMapAdapter.class)
                .build());
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

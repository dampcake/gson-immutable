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

import com.google.common.collect.ImmutableSortedSet;
import com.google.gson.TypeAdapter;

import java.util.SortedSet;

/**
 * Type adapter responsible for {@link ImmutableSortedSet}.
 *
 * @author Adam Peck
 */
public class ImmutableSortedSetAdapter extends DelegateAdapter<SortedSet<?>> {

    /**
     * @see DelegateAdapter#DelegateAdapter(TypeAdapter)
     */
    public ImmutableSortedSetAdapter(TypeAdapter<SortedSet<?>> delegate) {
        super(delegate);
    }

    /**
     * @see DelegateAdapter#transform(Object)
     */
    @Override
    protected SortedSet<?> transform(SortedSet<?> set) {
        return ImmutableSortedSet.copyOf(set);
    }
}

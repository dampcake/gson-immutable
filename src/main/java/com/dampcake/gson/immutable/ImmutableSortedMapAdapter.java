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

import java.util.SortedMap;

import com.google.common.collect.ImmutableSortedMap;
import com.google.gson.TypeAdapter;

/**
 * Type adapter responsible for {@link ImmutableSortedMap}.
 * 
 * @author Adam Peck
 */
public class ImmutableSortedMapAdapter extends DelegateAdapter<SortedMap<?, ?>> {
	
	/**
	 * @see DelegateAdapter#DelegateAdapter(TypeAdapter)
	 */
	public ImmutableSortedMapAdapter(TypeAdapter<SortedMap<?, ?>> delegate) {
		super(delegate);
	}
	
	/**
	 * @see DelegateAdapter#DelegateAdapter(TypeAdapter)
	 */
	@Override
	protected SortedMap<?, ?> transform(SortedMap<?, ?> map) {
		return ImmutableSortedMap.copyOf(map);
	}
}

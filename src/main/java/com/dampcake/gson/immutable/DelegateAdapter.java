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

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * A {@link TypeAdapter} that transforms the object returned by a delegate {@link TypeAdapter}.
 * 
 * @author Adam Peck
 */
public abstract class DelegateAdapter<T> extends TypeAdapter<T> {

	private final TypeAdapter<T> delegate;

	/**
	 * Create and set the delegate {@link TypeAdapter} to be used.
	 * 
	 * @param delegate the delegate {@link TypeAdapter}.
	 */
	public DelegateAdapter(TypeAdapter<T> delegate) {
		this.delegate = delegate;
	}

	/**
	 * @see TypeAdapter#write(JsonWriter, Object)
	 */
	@Override
	public void write(JsonWriter writer, T t) throws IOException {
		delegate.write(writer, t);
	}

	/**
	 * @see TypeAdapter#read(JsonReader)
	 */
	@Override
	public T read(JsonReader reader) throws IOException {
		T t = delegate.read(reader);
		
		if (t == null)
			return null;
		
		return transform(t);
	}

	/**
	 * Override to transform default Gson de-serialized object.
	 * 
	 * @param t the converted Java object.
	 * @return the transformed Java object.
	 */
	protected abstract T transform(T t);
}

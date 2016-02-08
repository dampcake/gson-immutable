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

import com.google.common.base.Preconditions;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

abstract class DelegateAdapter<T> extends TypeAdapter<T> {

    private final TypeAdapter<T> delegate;

    public DelegateAdapter(TypeAdapter<T> delegate) {
        Preconditions.checkNotNull(delegate, "delegate cannot be null");

        this.delegate = delegate;
    }

    @Override
    public void write(JsonWriter writer, T t) throws IOException {
        delegate.write(writer, t);
    }

    @Override
    public T read(JsonReader reader) throws IOException {
        T t = delegate.read(reader);

        if (t == null)
            return null;

        return transform(t);
    }

    protected abstract T transform(T t);
}

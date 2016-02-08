package com.dampcake.gson.immutable;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * Unit tests for types that are generic.
 */
public class GenericTest extends BaseTest {

    @Test
    public void testGenericClassInterface() {
        List<Generic<String>> data = ImmutableList.of(new Generic<String>("test1"), new Generic<String>("test2"));
        TypeToken<Collection<Generic<String>>> type = new TypeToken<Collection<Generic<String>>>() {
        };
        Collection<Generic<String>> collection = gson.fromJson(gson.toJson(data), type.getType());

        assertEquals(data, collection);
        assertNotSame(data, collection);

        exception.expect(UnsupportedOperationException.class);
        collection.add(new Generic<String>("test3"));
    }

    @Test
    public void testGenericClassGuava() {
        List<Generic<String>> data = ImmutableList.of(new Generic<String>("test1"), new Generic<String>("test2"));
        TypeToken<ImmutableCollection<Generic<String>>> type = new TypeToken<ImmutableCollection<Generic<String>>>() {
        };
        Collection<Generic<String>> collection = gson.fromJson(gson.toJson(data), type.getType());

        assertEquals(data, collection);
        assertNotSame(data, collection);

        exception.expect(UnsupportedOperationException.class);
        collection.add(new Generic<String>("test3"));
    }

    private static class Generic<T> {
        private final T value;

        public Generic(T value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Generic)) return false;

            Generic g = (Generic) o;

            return Objects.equals(value, g.value);
        }
    }
}

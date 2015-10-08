package com.dampcake.gson.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

/**
 * Unit tests for ImmutableAdapterFactory.
 */
public class ImmutableAdapterFactoryTest {

    private Gson gson = new Gson();

    @Test
    public void testForGuava() {
        TypeAdapterFactory factory = ImmutableAdapterFactory.forGuava();

        assertThat(factory.create(gson, TestTypes.I_COLLECTION_TYPE), instanceOf(ImmutableCollectionAdapter.class));
        assertThat(factory.create(gson, TestTypes.I_LIST_TYPE), instanceOf(ImmutableListAdapter.class));
        assertThat(factory.create(gson, TestTypes.I_SET_TYPE), instanceOf(ImmutableSetAdapter.class));
        assertThat(factory.create(gson, TestTypes.I_SSET_TYPE), instanceOf(ImmutableSortedSetAdapter.class));
        assertThat(factory.create(gson, TestTypes.I_MAP_TYPE), instanceOf(ImmutableMapAdapter.class));
        assertThat(factory.create(gson, TestTypes.I_SMAP_TYPE), instanceOf(ImmutableSortedMapAdapter.class));
    }

    @Test
    public void testForJava() {
        TypeAdapterFactory factory = ImmutableAdapterFactory.forJava();

        assertThat(factory.create(gson, TestTypes.COLLECTION_TYPE), instanceOf(ImmutableCollectionAdapter.class));
        assertThat(factory.create(gson, TestTypes.LIST_TYPE), instanceOf(ImmutableListAdapter.class));
        assertThat(factory.create(gson, TestTypes.SET_TYPE), instanceOf(ImmutableSetAdapter.class));
        assertThat(factory.create(gson, TestTypes.SSET_TYPE), instanceOf(ImmutableSortedSetAdapter.class));
        assertThat(factory.create(gson, TestTypes.MAP_TYPE), instanceOf(ImmutableMapAdapter.class));
        assertThat(factory.create(gson, TestTypes.SMAP_TYPE), instanceOf(ImmutableSortedMapAdapter.class));
    }

    @Test
    public void testInvalidTypes() {
        TypeAdapterFactory guava = ImmutableAdapterFactory.forGuava();
        TypeAdapterFactory java = ImmutableAdapterFactory.forJava();

        assertNull(guava.create(gson, TestTypes.LIST_TYPE));
        assertNull(java.create(gson, TestTypes.I_LIST_TYPE));
    }

    @Test
    public void testNullType() {
        TypeAdapterFactory factory = new ImmutableAdapterFactory(ImmutableMap.<Class, Class<? extends TypeAdapter>>builder().put(Collection.class, ExceptionThrowingAdapter.class).build());

        assertNull(factory.create(gson, TestTypes.COLLECTION_TYPE));
    }

    @Test(expected = NullPointerException.class)
    public void testForGuavaCreateNullType() {
        ImmutableAdapterFactory.forGuava().create(gson, null);
    }

    @Test(expected = NullPointerException.class)
    public void testForJavaCreateNullType() {
        ImmutableAdapterFactory.forJava().create(gson, null);
    }

    /**
     * Throws an exception when created to test exception handling in {@link ImmutableAdapterFactory#create(Gson, TypeToken)}.
     */
    private static class ExceptionThrowingAdapter extends DelegateAdapter<Collection<?>> {
        /**
         * @see DelegateAdapter#DelegateAdapter(TypeAdapter)
         */
        public ExceptionThrowingAdapter(TypeAdapter<Collection<?>> delegate) {
            super(delegate);
            throw new NullPointerException();
        }

        /**
         * @see DelegateAdapter#transform(Object)
         */
        @Override
        protected Collection<?> transform(Collection<?> collection) {
            return ImmutableList.copyOf(collection);
        }
    }
}

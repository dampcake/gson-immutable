package com.dampcake.gson.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

/**
 * Unit tests for ImmutableAdapterFactory.
 */
public class ImmutableAdapterFactoryTest {

    private GsonBuilder builder;

    @Before
    public void setUp() {
        builder = new GsonBuilder();
    }

    @Test
    public void testRegister() {
        ImmutableAdapterFactory.registerOn(builder);
        Gson gson = builder.create();

        assertThat(gson.getAdapter(TestTypes.I_COLLECTION_TYPE), instanceOf(ImmutableCollectionAdapter.class));
        assertThat(gson.getAdapter(TestTypes.I_LIST_TYPE), instanceOf(ImmutableListAdapter.class));
        assertThat(gson.getAdapter(TestTypes.I_SET_TYPE), instanceOf(ImmutableSetAdapter.class));
        assertThat(gson.getAdapter(TestTypes.I_SSET_TYPE), instanceOf(ImmutableSortedSetAdapter.class));
        assertThat(gson.getAdapter(TestTypes.I_MAP_TYPE), instanceOf(ImmutableMapAdapter.class));
        assertThat(gson.getAdapter(TestTypes.I_SMAP_TYPE), instanceOf(ImmutableSortedMapAdapter.class));
        assertThat(gson.getAdapter(TestTypes.I_MSET_TYPE), instanceOf(ImmutableMultisetAdapter.class));

        assertThat(gson.getAdapter(TestTypes.COLLECTION_TYPE), not(instanceOf(ImmutableCollectionAdapter.class)));
        assertThat(gson.getAdapter(TestTypes.LIST_TYPE), not(instanceOf(ImmutableListAdapter.class)));
        assertThat(gson.getAdapter(TestTypes.SET_TYPE), not(instanceOf(ImmutableSetAdapter.class)));
        assertThat(gson.getAdapter(TestTypes.SSET_TYPE), not(instanceOf(ImmutableSortedSetAdapter.class)));
        assertThat(gson.getAdapter(TestTypes.NSET_TYPE), not(instanceOf(ImmutableSortedSetAdapter.class)));
        assertThat(gson.getAdapter(TestTypes.MAP_TYPE), not(instanceOf(ImmutableMapAdapter.class)));
        assertThat(gson.getAdapter(TestTypes.SMAP_TYPE), not(instanceOf(ImmutableSortedMapAdapter.class)));
        assertThat(gson.getAdapter(TestTypes.MSET_TYPE), not(instanceOf(ImmutableMultisetAdapter.class)));
    }

    @Test
    public void testRegisterWithInterfaces() {
        ImmutableAdapterFactory.registerOn(builder, true);
        Gson gson = builder.create();

        assertThat(gson.getAdapter(TestTypes.I_COLLECTION_TYPE), instanceOf(ImmutableCollectionAdapter.class));
        assertThat(gson.getAdapter(TestTypes.I_LIST_TYPE), instanceOf(ImmutableListAdapter.class));
        assertThat(gson.getAdapter(TestTypes.I_SET_TYPE), instanceOf(ImmutableSetAdapter.class));
        assertThat(gson.getAdapter(TestTypes.I_SSET_TYPE), instanceOf(ImmutableSortedSetAdapter.class));
        assertThat(gson.getAdapter(TestTypes.I_MAP_TYPE), instanceOf(ImmutableMapAdapter.class));
        assertThat(gson.getAdapter(TestTypes.I_SMAP_TYPE), instanceOf(ImmutableSortedMapAdapter.class));
        assertThat(gson.getAdapter(TestTypes.I_MSET_TYPE), instanceOf(ImmutableMultisetAdapter.class));

        assertThat(gson.getAdapter(TestTypes.COLLECTION_TYPE), instanceOf(ImmutableCollectionAdapter.class));
        assertThat(gson.getAdapter(TestTypes.LIST_TYPE), instanceOf(ImmutableListAdapter.class));
        assertThat(gson.getAdapter(TestTypes.SET_TYPE), instanceOf(ImmutableSetAdapter.class));
        assertThat(gson.getAdapter(TestTypes.SSET_TYPE), instanceOf(ImmutableSortedSetAdapter.class));
        assertThat(gson.getAdapter(TestTypes.NSET_TYPE), instanceOf(ImmutableSortedSetAdapter.class));
        assertThat(gson.getAdapter(TestTypes.MAP_TYPE), instanceOf(ImmutableMapAdapter.class));
        assertThat(gson.getAdapter(TestTypes.SMAP_TYPE), instanceOf(ImmutableSortedMapAdapter.class));
        assertThat(gson.getAdapter(TestTypes.MSET_TYPE), instanceOf(ImmutableMultisetAdapter.class));
    }

    @Test
    public void testNullType() {
        TypeAdapterFactory factory = new ImmutableAdapterFactory(ImmutableMap.<Class, Class<? extends TypeAdapter>>builder().put(Collection.class, ExceptionThrowingAdapter.class).build());

        assertNull(factory.create(builder.create(), TestTypes.COLLECTION_TYPE));
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

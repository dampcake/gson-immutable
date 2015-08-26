package com.dampcake.gson.immutable;

import com.google.gson.TypeAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for DelegateAdapter.
 */
@RunWith(MockitoJUnitRunner.class)
public class DelegateAdapterTest {

    private static final String TEST_STRING = "string";

    @Mock
    private TypeAdapter<String> delegate;
    private DelegateAdapter<String> instance;

    @Before
    public void setUp() {
        instance = new DelegateAdapter<String>(delegate) {
            @Override
            protected String transform(String s) {
                return s.toUpperCase();
            }
        };
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullDelegate() {
        new DelegateAdapter<String>(null) {
            @Override
            protected String transform(String s) {
                return null;
            }
        };
    }

    @Test
    public void testWrite() throws Exception {
        instance.write(null, "string");

        verify(delegate).write(null, "string");
    }

    @Test
    public void testRead() throws Exception {
        when(delegate.read(null)).thenReturn(TEST_STRING);

        assertEquals(TEST_STRING.toUpperCase(), instance.read(null));
    }

    @Test
    public void testReadNull() throws Exception {
        when(delegate.read(null)).thenReturn(null);

        assertNull(instance.read(null));
    }
}

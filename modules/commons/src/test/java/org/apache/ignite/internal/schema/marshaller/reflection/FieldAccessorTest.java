/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.schema.marshaller.reflection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import org.apache.ignite.internal.schema.Bitmask;
import org.apache.ignite.internal.schema.Column;
import org.apache.ignite.internal.schema.TestUtils;
import org.apache.ignite.internal.schema.Tuple;
import org.apache.ignite.internal.schema.TupleAssembler;
import org.apache.ignite.internal.schema.marshaller.BinaryMode;
import org.apache.ignite.internal.schema.marshaller.SerializationException;
import org.apache.ignite.internal.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.apache.ignite.internal.schema.NativeType.BYTE;
import static org.apache.ignite.internal.schema.NativeType.BYTES;
import static org.apache.ignite.internal.schema.NativeType.DOUBLE;
import static org.apache.ignite.internal.schema.NativeType.FLOAT;
import static org.apache.ignite.internal.schema.NativeType.INTEGER;
import static org.apache.ignite.internal.schema.NativeType.LONG;
import static org.apache.ignite.internal.schema.NativeType.SHORT;
import static org.apache.ignite.internal.schema.NativeType.STRING;
import static org.apache.ignite.internal.schema.NativeType.UUID;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Check field accessor correctness.
 */
public class FieldAccessorTest {
    /** Random. */
    private Random rnd;

    /**
     *
     */
    @BeforeEach
    public void initRandom() {
        long seed = System.currentTimeMillis();

        System.out.println("Using seed: " + seed + "L;");

        rnd = new Random(seed);
    }

    /**
     * @throws Exception If failed.
     */
    @Test
    public void testFieldAccessor() throws Exception {
        Column[] cols = new Column[] {
            new Column("pByteCol", BYTE, false),
            new Column("pShortCol", SHORT, false),
            new Column("pIntCol", INTEGER, false),
            new Column("pLongCol", LONG, false),
            new Column("pFloatCol", FLOAT, false),
            new Column("pDoubleCol", DOUBLE, false),

            new Column("byteCol", BYTE, false),
            new Column("shortCol", SHORT, false),
            new Column("intCol", INTEGER, false),
            new Column("longCol", LONG, false),
            new Column("floatCol", FLOAT, false),
            new Column("doubleCol", DOUBLE, false),

            new Column("uuidCol", UUID, false),
            new Column("bitmaskCol", Bitmask.of(9), false),
            new Column("stringCol", STRING, false),
            new Column("bytesCol", BYTES, false),
        };

        final Pair<TupleAssembler, Tuple> mocks = createMocks();

        final TupleAssembler tupleAssembler = mocks.getFirst();
        final Tuple tuple = mocks.getSecond();

        final TestObject obj = TestObject.randomObject(rnd);

        for (int i = 0; i < cols.length; i++) {
            FieldAccessor accessor = FieldAccessor.create(TestObject.class, cols[i], i);

            accessor.write(tupleAssembler, obj);
        }

        final TestObject restoredObj = new TestObject();

        for (int i = 0; i < cols.length; i++) {
            FieldAccessor accessor = FieldAccessor.create(TestObject.class, cols[i], i);

            accessor.read(tuple, restoredObj);
        }

        assertEquals(obj.pByteCol, restoredObj.pByteCol);
        assertEquals(obj.pShortCol, restoredObj.pShortCol);
        assertEquals(obj.pIntCol, restoredObj.pIntCol);
        assertEquals(obj.pLongCol, restoredObj.pLongCol);
        assertEquals(obj.pFloatCol, restoredObj.pFloatCol);
        assertEquals(obj.pDoubleCol, restoredObj.pDoubleCol);

        assertEquals(obj.byteCol, restoredObj.byteCol);
        assertEquals(obj.shortCol, restoredObj.shortCol);
        assertEquals(obj.intCol, restoredObj.intCol);
        assertEquals(obj.longCol, restoredObj.longCol);
        assertEquals(obj.floatCol, restoredObj.floatCol);
        assertEquals(obj.doubleCol, restoredObj.doubleCol);

        assertEquals(obj.uuidCol, restoredObj.uuidCol);
        assertEquals(obj.bitmaskCol, restoredObj.bitmaskCol);
        assertEquals(obj.stringCol, restoredObj.stringCol);
        assertArrayEquals(obj.bytesCol, restoredObj.bytesCol);
    }

    /**
     * @throws Exception If failed.
     */
    @Test
    public void testNullableFieldsAccessor() throws Exception {
        Column[] cols = new Column[] {
            new Column("intCol", INTEGER, true),
            new Column("longCol", LONG, true),

            new Column("stringCol", STRING, true),
            new Column("bytesCol", BYTES, true),
        };

        final Pair<TupleAssembler, Tuple> mocks = createMocks();

        final TupleAssembler tupleAssembler = mocks.getFirst();
        final Tuple tuple = mocks.getSecond();

        final TestSimpleObject obj = new TestSimpleObject();
        obj.longCol = rnd.nextLong();
        obj.stringCol = TestUtils.randomString(rnd, 255);

        for (int i = 0; i < cols.length; i++) {
            FieldAccessor accessor = FieldAccessor.create(TestSimpleObject.class, cols[i], i);

            accessor.write(tupleAssembler, obj);
        }

        final TestSimpleObject restoredObj = new TestSimpleObject();

        for (int i = 0; i < cols.length; i++) {
            FieldAccessor accessor = FieldAccessor.create(TestSimpleObject.class, cols[i], i);

            accessor.read(tuple, restoredObj);
        }

        assertEquals(obj.intCol, restoredObj.intCol);
        assertEquals(obj.longCol, restoredObj.longCol);

        assertEquals(obj.stringCol, restoredObj.stringCol);
        assertArrayEquals(obj.bytesCol, restoredObj.bytesCol);
    }

    /**
     * @throws Exception If failed.
     */
    @Test
    public void testIdentityAccessor() throws Exception {
        final FieldAccessor accessor = FieldAccessor.createIdentityAccessor(
            new Column("col0", STRING, true),
            0,
            BinaryMode.STRING);

        assertEquals("Some string", accessor.value("Some string"));

        final Pair<TupleAssembler, Tuple> mocks = createMocks();

        accessor.write(mocks.getFirst(), "Other string");
        assertEquals("Other string", accessor.read(mocks.getSecond()));
    }

    /**
     * @throws Exception If failed.
     */
    @Test
    public void testWrongIdentityAccessor() throws Exception {
        final FieldAccessor accessor = FieldAccessor.createIdentityAccessor(
            new Column("col0", STRING, true),
            42,
            BinaryMode.UUID);

        assertEquals("Some string", accessor.value("Some string"));

        final Pair<TupleAssembler, Tuple> mocks = createMocks();

        assertThrows(
            SerializationException.class,
            () -> accessor.write(mocks.getFirst(), "Other string"),
            "Failed to write field [id=42]"
        );
    }

    /**
     * Creates mock pair for {@link Tuple} and {@link TupleAssembler).
     *
     * @return Pair of mocks.
     */
    private Pair<TupleAssembler, Tuple> createMocks() {
        final ArrayList<Object> vals = new ArrayList<>();

        final TupleAssembler mockedAsm = Mockito.mock(TupleAssembler.class);
        final Tuple mockedTuple = Mockito.mock(Tuple.class);

        final Answer<Void> asmAnswer = new Answer<Void>() {
            @Override public Void answer(InvocationOnMock invocation) {
                if ("appendNull".equals(invocation.getMethod().getName()))
                    vals.add(null);
                else
                    vals.add(invocation.getArguments()[0]);

                return null;
            }
        };

        final Answer<Object> tupleAnswer = new Answer<Object>() {
            @Override public Object answer(InvocationOnMock invocation) {
                final int idx = invocation.getArgument(0, Integer.class);

                return vals.get(idx);
            }
        };

        Mockito.doAnswer(asmAnswer).when(mockedAsm).appendNull();
        Mockito.doAnswer(asmAnswer).when(mockedAsm).appendByte(Mockito.anyByte());
        Mockito.doAnswer(asmAnswer).when(mockedAsm).appendShort(Mockito.anyShort());
        Mockito.doAnswer(asmAnswer).when(mockedAsm).appendInt(Mockito.anyInt());
        Mockito.doAnswer(asmAnswer).when(mockedAsm).appendLong(Mockito.anyLong());
        Mockito.doAnswer(asmAnswer).when(mockedAsm).appendFloat(Mockito.anyFloat());
        Mockito.doAnswer(asmAnswer).when(mockedAsm).appendDouble(Mockito.anyDouble());

        Mockito.doAnswer(asmAnswer).when(mockedAsm).appendUuid(Mockito.any(java.util.UUID.class));
        Mockito.doAnswer(asmAnswer).when(mockedAsm).appendBitmask(Mockito.any(BitSet.class));
        Mockito.doAnswer(asmAnswer).when(mockedAsm).appendString(Mockito.anyString());
        Mockito.doAnswer(asmAnswer).when(mockedAsm).appendBytes(Mockito.any(byte[].class));

        Mockito.doAnswer(tupleAnswer).when(mockedTuple).byteValue(Mockito.anyInt());
        Mockito.doAnswer(tupleAnswer).when(mockedTuple).byteValueBoxed(Mockito.anyInt());
        Mockito.doAnswer(tupleAnswer).when(mockedTuple).shortValue(Mockito.anyInt());
        Mockito.doAnswer(tupleAnswer).when(mockedTuple).shortValueBoxed(Mockito.anyInt());
        Mockito.doAnswer(tupleAnswer).when(mockedTuple).intValue(Mockito.anyInt());
        Mockito.doAnswer(tupleAnswer).when(mockedTuple).intValueBoxed(Mockito.anyInt());
        Mockito.doAnswer(tupleAnswer).when(mockedTuple).longValue(Mockito.anyInt());
        Mockito.doAnswer(tupleAnswer).when(mockedTuple).longValueBoxed(Mockito.anyInt());
        Mockito.doAnswer(tupleAnswer).when(mockedTuple).floatValue(Mockito.anyInt());
        Mockito.doAnswer(tupleAnswer).when(mockedTuple).floatValueBoxed(Mockito.anyInt());
        Mockito.doAnswer(tupleAnswer).when(mockedTuple).doubleValue(Mockito.anyInt());
        Mockito.doAnswer(tupleAnswer).when(mockedTuple).doubleValueBoxed(Mockito.anyInt());

        Mockito.doAnswer(tupleAnswer).when(mockedTuple).uuidValue(Mockito.anyInt());
        Mockito.doAnswer(tupleAnswer).when(mockedTuple).bitmaskValue(Mockito.anyInt());
        Mockito.doAnswer(tupleAnswer).when(mockedTuple).stringValue(Mockito.anyInt());
        Mockito.doAnswer(tupleAnswer).when(mockedTuple).bytesValue(Mockito.anyInt());

        return new Pair<>(mockedAsm, mockedTuple);
    }

    /**
     * Test object.
     */
    private static class TestObject {
        /**
         * @return Random TestObject.
         */
        public static TestObject randomObject(Random rnd) {
            final TestObject obj = new TestObject();

            obj.pByteCol = (byte)rnd.nextInt(255);
            obj.pShortCol = (short)rnd.nextInt(65535);
            obj.pIntCol = rnd.nextInt();
            obj.pLongCol = rnd.nextLong();
            obj.pFloatCol = rnd.nextFloat();
            obj.pDoubleCol = rnd.nextDouble();

            obj.byteCol = (byte)rnd.nextInt(255);
            obj.shortCol = (short)rnd.nextInt(65535);
            obj.intCol = rnd.nextInt();
            obj.longCol = rnd.nextLong();
            obj.floatCol = rnd.nextFloat();
            obj.doubleCol = rnd.nextDouble();

            obj.uuidCol = new UUID(rnd.nextLong(), rnd.nextLong());
            obj.bitmaskCol = TestUtils.randomBitSet(rnd, rnd.nextInt(42));
            obj.stringCol = TestUtils.randomString(rnd, rnd.nextInt(255));
            obj.bytesCol = TestUtils.randomBytes(rnd, rnd.nextInt(255));

            return obj;
        }

        // Primitive typed
        private byte pByteCol;

        private short pShortCol;

        private int pIntCol;

        private long pLongCol;

        private float pFloatCol;

        private double pDoubleCol;

        // Reference typed
        private Byte byteCol;

        private Short shortCol;

        private Integer intCol;

        private Long longCol;

        private Float floatCol;

        private Double doubleCol;

        private UUID uuidCol;

        private BitSet bitmaskCol;

        private String stringCol;

        private byte[] bytesCol;

        /** {@inheritDoc} */
        @Override public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            TestObject object = (TestObject)o;
            return pByteCol == object.pByteCol &&
                pShortCol == object.pShortCol &&
                pIntCol == object.pIntCol &&
                pLongCol == object.pLongCol &&
                Float.compare(object.pFloatCol, pFloatCol) == 0 &&
                Double.compare(object.pDoubleCol, pDoubleCol) == 0 &&
                Objects.equals(byteCol, object.byteCol) &&
                Objects.equals(shortCol, object.shortCol) &&
                Objects.equals(intCol, object.intCol) &&
                Objects.equals(longCol, object.longCol) &&
                Objects.equals(floatCol, object.floatCol) &&
                Objects.equals(doubleCol, object.doubleCol) &&
                Objects.equals(uuidCol, object.uuidCol) &&
                Objects.equals(bitmaskCol, object.bitmaskCol) &&
                Objects.equals(stringCol, object.stringCol) &&
                Arrays.equals(bytesCol, object.bytesCol);
        }

        /** {@inheritDoc} */
        @Override public int hashCode() {
            return 73;
        }
    }

    /**
     * Test object.
     */
    private static class TestSimpleObject {
        Long longCol;

        Integer intCol;

        byte[] bytesCol;

        String stringCol;

        /** {@inheritDoc} */
        @Override public boolean equals(Object o) {
            if (this == o)
                return true;

            if (o == null || getClass() != o.getClass())
                return false;

            TestSimpleObject object = (TestSimpleObject)o;

            return Objects.equals(longCol, object.longCol) &&
                Objects.equals(intCol, object.intCol) &&
                Arrays.equals(bytesCol, object.bytesCol) &&
                Objects.equals(stringCol, object.stringCol);
        }

        /** {@inheritDoc} */
        @Override public int hashCode() {
            return 42;
        }
    }
}
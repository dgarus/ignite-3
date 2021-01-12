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

import org.apache.ignite.internal.schema.Columns;
import org.apache.ignite.internal.schema.SchemaDescriptor;
import org.apache.ignite.internal.schema.Tuple;
import org.apache.ignite.internal.schema.TupleAssembler;
import org.apache.ignite.internal.schema.marshaller.AbstractSerializer;
import org.apache.ignite.internal.schema.marshaller.SerializationException;
import org.jetbrains.annotations.Nullable;

import static org.apache.ignite.internal.schema.marshaller.MarshallerUtil.getValueSize;

/**
 * Reflection based (de)serializer.
 */
public class JavaSerializer extends AbstractSerializer {
    /** Key class. */
    private final Class<?> keyClass;

    /** Value class. */
    private final Class<?> valClass;

    /** Key marshaller. */
    private final Marshaller keyMarsh;

    /** Value marshaller. */
    private final Marshaller valMarsh;

    /**
     * Constructor.
     *
     * @param schema Schema.
     * @param keyClass Key type.
     * @param valClass Value type.
     */
    public JavaSerializer(SchemaDescriptor schema, Class<?> keyClass, Class<?> valClass) {
        super(schema);
        this.keyClass = keyClass;
        this.valClass = valClass;

        keyMarsh = Marshaller.createMarshaller(schema.keyColumns(), 0, keyClass);
        valMarsh = Marshaller.createMarshaller(schema.valueColumns(), schema.keyColumns().length(), valClass);
    }

    /** {@inheritDoc} */
    @Override protected  byte[] serialize0(TupleAssembler asm, Object key, @Nullable Object val)
        throws SerializationException {
        assert keyClass.isInstance(key);
        assert val == null || valClass.isInstance(val);

        keyMarsh.writeObject(key, asm);
        valMarsh.writeObject(val, asm);

        return asm.build();
    }

    /**
     * Creates TupleAssebler for key-value pair.
     *
     * @param key Key object.
     * @param val Value object.
     * @return Tuple assembler.
     */
    @Override protected TupleAssembler createAssembler(Object key, Object val) {
        ObjectStatistic keyStat = collectObjectStats(schema.keyColumns(), keyMarsh, key);
        ObjectStatistic valStat = collectObjectStats(schema.valueColumns(), valMarsh, val);

        int size = TupleAssembler.tupleSize(
            schema.keyColumns(), keyStat.nonNullFields, keyStat.nonNullFieldsSize,
            schema.valueColumns(), valStat.nonNullFields, valStat.nonNullFieldsSize);

        return new TupleAssembler(schema, size, keyStat.nonNullFields, valStat.nonNullFields);
    }

    /**
     * Reads object fields and gather statistic.
     *
     * @param cols Schema columns.
     * @param marsh Marshaller.
     * @param obj Object.
     * @return Object statistic.
     */
    private ObjectStatistic collectObjectStats(Columns cols, Marshaller marsh, Object obj) {
        if (obj == null || cols.firstVarlengthColumn() < 0 /* No varlen columns */)
            return new ObjectStatistic(0, 0);

        int cnt = 0;
        int size = 0;

        for (int i = cols.firstVarlengthColumn(); i < cols.length(); i++) {
            final Object val = marsh.value(obj, i);

            if (val == null || cols.column(i).type().spec().fixedLength())
                continue;

            size += getValueSize(val, cols.column(i).type());
            cnt++;
        }

        return new ObjectStatistic(cnt, size);
    }

    /** {@inheritDoc} */
    @Override protected Object deserializeKey0(Tuple tuple) throws SerializationException {
        final Object o = keyMarsh.readObject(tuple);

        assert keyClass.isInstance(o);

        return o;
    }

    /** {@inheritDoc} */
    @Override protected Object deserializeValue0(Tuple tuple) throws SerializationException {
        final Object o = valMarsh.readObject(tuple);

        assert o == null || valClass.isInstance(o);

        return o;
    }

    /**
     * Object statistic.
     */
    private static class ObjectStatistic {
        /** Non-null fields of varlen type. */
        int nonNullFields;

        /** Length of all non-null fields of varlen types. */
        int nonNullFieldsSize;

        /** Constructor. */
        ObjectStatistic(int nonNullFields, int nonNullFieldsSize) {
            this.nonNullFields = nonNullFields;
            this.nonNullFieldsSize = nonNullFieldsSize;
        }
    }
}
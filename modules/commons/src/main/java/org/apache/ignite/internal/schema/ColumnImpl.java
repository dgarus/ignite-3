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

package org.apache.ignite.internal.schema;

import java.util.Objects;
import org.apache.ignite.schema.Column;
import org.apache.ignite.schema.ColumnType;
import org.jetbrains.annotations.Nullable;

/**
 * Table column.
 */
public class ColumnImpl implements Column {
    /** Column name. */
    private final String name;

    /** Column type. */
    private final ColumnType type;

    /** Nullable flag. */
    private final boolean nullable;

    /** Default value. */
    private final Object defVal;

    /**
     * Constructor.
     *
     * @param name Column name.
     * @param type Column type.
     * @param nullable Nullable flag.
     * @param defVal Default value.
     */
    public ColumnImpl(String name, ColumnType type, boolean nullable, @Nullable Object defVal) {
        this.name = name;
        this.type = type;
        this.nullable = nullable;
        this.defVal = defVal;
    }

    /** {@inheritDoc} */
    @Override public ColumnType type() {
        return type;
    }

    /** {@inheritDoc} */
    @Override public String name() {
        return name;
    }

    /** {@inheritDoc} */
    @Override public boolean nullable() {
        return nullable;
    }

    /** {@inheritDoc} */
    @Override public Object defaultValue() {
        return defVal;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ColumnImpl column = (ColumnImpl)o;

        return nullable == column.nullable && name.equals(column.name) && type.equals(column.type) && Objects.equals(defVal, column.defVal);
    }

    @Override public int hashCode() {
        return Objects.hash(name, type, nullable, defVal);
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return "ColumnImpl[" +
            "name='" + name + '\'' +
            ", type=" + type +
            ", nullable=" + nullable +
            ", default=" + defVal +
            ']';
    }
}
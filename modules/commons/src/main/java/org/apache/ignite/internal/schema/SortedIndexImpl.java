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

import java.util.Collection;
import org.apache.ignite.schema.IndexColumn;
import org.apache.ignite.schema.SortedIndex;

public class SortedIndexImpl implements SortedIndex {
    private final String name;

    public SortedIndexImpl(String name) {
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override public int inlineSize() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override public Collection<IndexColumn> columns() {
        return null;
    }

    /** {@inheritDoc} */
    @Override public Collection<IndexColumn> indexedColumns() {
        return null;
    }

    /** {@inheritDoc} */
    @Override public String name() {
        return name;
    }
}
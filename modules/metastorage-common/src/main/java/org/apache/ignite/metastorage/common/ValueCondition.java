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

package org.apache.ignite.metastorage.common;

/**
 * Condition, intended for value evaluation.
 */
public class ValueCondition extends AbstractCondition {
    /** Value. */
    private final byte[] val;

    /**
     * Constructor.
     *
     * @param type Condition type.
     * @param val Value for comparison.
     */
    public ValueCondition(ConditionType type, byte[] val) {
        super(type);

        this.val = val;
    }

    /**
     * Compares a given value with a target one in lexicographical manner.
     *
     * @param e A target entry.
     * @return Comparison result as defined {@link java.util.Comparator} contract.
     */
    // TODO: Actually, value could be compared in different manners. So we should have possibility to define comparison logic.
    @Override protected int compare(Entry e) {
        return LexicographicComparator.INSTANCE.compare(e.value(), val);
    }
}

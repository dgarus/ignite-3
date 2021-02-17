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

import static org.apache.ignite.metastorage.common.ConditionType.EQUAL;
import static org.apache.ignite.metastorage.common.ConditionType.GREATER;
import static org.apache.ignite.metastorage.common.ConditionType.LESS;
import static org.apache.ignite.metastorage.common.ConditionType.NOT_EQUAL;

/**
 * Abstract condition which is designed for a comparison based conditions.
 *
 * <p>The comparison type is defined by {@link ConditionType} enumeration. Only {@link #compare(Entry)} method
 * should be implemented for getting correct comparison based condition evaluation.</p>
 */
public abstract class AbstractCondition implements Condition {
    /** Condition type. */
    private final ConditionType type;

    /**
     * Constructor.
     *
     * @param type Condition type.
     */
    public AbstractCondition(ConditionType type) {
        this.type = type;
    }

    /**
     * Returns condition type for this condition.
     *
     * @return Condition type.
     */
    protected ConditionType conditionType() {
        return type;
    }

    /**
     * Evaluates comparison based condition.
     *
     * @param e Entry which is a subject of conditional update.
     * @return {@code True} if condition is successful, otherwise - {@code false}.
     */
    @Override public boolean eval(Entry e) {
        int res = compare(e);

        ConditionType type = conditionType();

        return (type == EQUAL && res == 0) ||
                (type == NOT_EQUAL && res != 0) ||
                (type == LESS && res < 0) ||
                (type == GREATER && res > 0);
    }

    /**
     * This abstract method should implement comparison logic based on {@link java.util.Comparator} contract.
     *
     * @param e Entry.
     * @return Comparison result as defined {@link java.util.Comparator} contract.
     */
    abstract protected int compare(Entry e);
}

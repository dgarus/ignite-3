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
 * Condition, intended for revision evaluation.
 */
public class RevisionCondition extends AbstractCondition {
    /** Revision. */
    private final long rev;

    /**
     * Constructor.
     *
     * @param type Condition type.
     * @param rev Revision for comparison.
     */
    public RevisionCondition(ConditionType type, long rev) {
        super(type);

        this.rev = rev;
    }

    /**
     * Compares a given revision with a target one.
     *
     * @param e A target entry.
     * @return Comparison result as defined {@link java.util.Comparator} contract.
     */
    @Override protected int compare(Entry e) {
        return Long.compare(e.revision(), rev);
    }
}

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

import org.jetbrains.annotations.NotNull;

/**
 * Put (write) update.
 */
public class PutUpdate implements Update {
    /** Key. */
    @NotNull
    private final byte[] key;

    /** Value. */
    @NotNull
    private final byte[] val;

    /**
     * Constructor.
     *
     * @param key A target key which will be updated.
     * @param val A target value which will be written for a given key.
     */
    public PutUpdate(@NotNull byte[] key, @NotNull byte[] val) {
        assert key != null : "key can't be null";
        assert val != null : "value can't be null";

        this.key = key;
        this.val = val;
    }
}

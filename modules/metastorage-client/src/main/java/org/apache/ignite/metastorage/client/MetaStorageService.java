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

package org.apache.ignite.metastorage.client;


import org.apache.ignite.metastorage.common.Condition;
import org.apache.ignite.metastorage.common.Entry;
import org.apache.ignite.metastorage.common.Update;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Defines interface for access to a metastorage service.
 */
public interface MetaStorageService {
    /**
     * Returns metastorage revision.
     *
     * @return Metastorage revision.
     */
    @NotNull
    Future<Long> revision();

    /**
     * Updates entry with given key and value.
     *
     * @param key Key. Couldn't be {@code null}.
     * @param value Value.Couldn't be {@code null}.
     * @return A previous entry which could be regular, empty or tombstone. Couldn't be {@code null}.
     * @see Entry
     */
    @NotNull
    Future<Entry> put(@NotNull byte[] key, @NotNull byte[] value);

    /**
     * Retrieves entry with a given key.
     *
     * @param key Key. Couldn't be {@code null}.
     * @return An entry for given key or an empty/tombstone entry. Couldn't be {@code null}.
     * @see Entry
     */
    @NotNull
    Future<Entry> get(@NotNull byte[] key);

    /**
     * Retrieves entry with a given key and a revision.
     *
     * @param key Key. Couldn't be {@code null}.
     * @param rev Revision. Must be positive.
     * @return An entry for given key and a revision or an empty/tombstone entry. Couldn't be {@code null}.
     * @see Entry
     */
    //TODO: Is it really needed???
    @NotNull
    Future<Entry> get(@NotNull byte[] key, long rev);

    /**
     * Removes an entry with a given key.
     *
     * @param key Key. Couldn't be {@code null}.
     * @return A previous entry which could be regular, empty or tombstone. Couldn't be {@code null}.
     * @see Entry
     */
    @NotNull
    Future<Entry> remove(@NotNull byte[] key);

    /**
     * Updates entry conditionally.
     *
     * @param key Key. Couldn't be {@code null}.
     * @param condition Condition.
     * @param success Update which will be applied in case of condition success.
     * @param failure Update which will be applied in case of condition failure.
     * @return A previous entry which could be regular, empty or tombstone. Couldn't be {@code null}.
     * @see Entry
     */
    Future<Entry> update(@NotNull byte[] key, Condition condition, Update success, Update failure);

    /**
     * Updates multiple entries conditionally.
     *
     * @param keys List of keys.
     * @param condition List of conditions corresponding to keys list.
     * @param success List of updates which will be applied to corresponding key in case of condition success.
     * @param failure List of updates which will be applied to corresponding key in case of condition failure.
     * @return A List of previous entries corresponding to list of keys, where each entry could be regular,
     * empty or tombstone. Couldn't be {@code null}.
     * @see Entry
     */
    // TODO: If I understand correctly, we always use one success and one failure condition for each key in transaction. May be I'm wrong.
    // TODO: Probably, we should provide no-op conditions also (e.g. for implementation if-then only logic).
    Future<List<Entry>> update(List<byte[]> keys, List<Condition> condition, List<Update> success, List<Update> failure);

    /**
     * Retrieves entries for a given key range in lexicographic order.
     * Only entries with the latest revisions will be returned.
     *
     * @param keyFrom Start key of range (inclusive). Couldn't be {@code null}.
     * @param keyTo End key of range (exclusive). Could be {@code null}.
     * @param consumer Entry consumer which will be invoked for each entry. Entry couldn't be {@code null}.
     * @return Future which will be completed when iteration will be finished. Couldn't be {@code null}.
     */
    @NotNull
    Future<Void> iterate(@NotNull byte[] keyFrom, @Nullable byte[] keyTo, @NotNull Consumer<Entry> consumer);

    /**
     * Creates watcher on metastorage updates with given parameters.
     *
     * @param keyFrom Start key of range (inclusive). Could be {@code null}.
     * @param keyTo End key of range (exclusive). Could be {@code null}.
     * @param revision Start revision.
     * @param consumer Entry consumer which will be invoked for each update. Entry couldn't be {@code null}.
     * @return Watch identifier.
     */
    @NotNull
    Future<Long> watch(@Nullable byte[] keyFrom, @Nullable byte[] keyTo, long revision,
                       @NotNull BiConsumer<Entry, Entry> consumer);

    /**
     * Stops watch with a given identifier.
     *
     * @param watchId Watch identifier.
     * @return Completed future in case of operation success. Couldn't be {@code null}.
     */
    @NotNull
    Future<Void> stopWatch(long watchId);

    /**
     * Compacts metastorage (removes all tombstone entries and old entries except of entries with latest revision).
     *
     * @return Completed future. Couldn't be {@code null}.
     */
    Future<Void> compact();
}


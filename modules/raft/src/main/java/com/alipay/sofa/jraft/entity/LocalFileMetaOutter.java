/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: local_file_meta.proto

package com.alipay.sofa.jraft.entity;

import com.alipay.sofa.jraft.util.ByteString;

public final class LocalFileMetaOutter {
    /**
     * Protobuf enum {@code jraft.FileSource}
     */
    public enum FileSource {
        /**
         * <code>FILE_SOURCE_LOCAL = 0;</code>
         */
        FILE_SOURCE_LOCAL(0),
        /**
         * <code>FILE_SOURCE_REFERENCE = 1;</code>
         */
        FILE_SOURCE_REFERENCE(1);

        public final int getNumber() {
            return value;
        }

        /**
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static FileSource valueOf(int value) {
            return forNumber(value);
        }

        public static FileSource forNumber(int value) {
            switch (value) {
                case 0:
                    return FILE_SOURCE_LOCAL;
                case 1:
                    return FILE_SOURCE_REFERENCE;
                default:
                    return null;
            }
        }

        private static final FileSource[] VALUES = values();

        private final int value;

        private FileSource(int value) {
            this.value = value;
        }
    }

    public interface LocalFileMeta {
        ByteString getUserMeta();

        FileSource getSource();

        java.lang.String getChecksum();

        interface Builder {
            LocalFileMeta build();
        }
    }
}

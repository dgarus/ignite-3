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
// source: cli.proto

package org.apache.ignite.raft.rpc;

public final class CliRequests {
    private CliRequests() {
    }

    public interface AddPeerRequest extends Message {
        String getGroupId();

        String getLeaderId();

        String getPeerId();

        interface Builder {
            Builder setGroupId(String groupId);

            Builder setLeaderId(String leaderId);

            Builder setPeerId(String peerId);

            AddPeerRequest build();
        }

        public static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createAddPeerRequest();
        }
    }

    public interface AddPeerResponse extends Message {
        static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createAddPeerResponse();
        }

        java.util.List<String> getOldPeersList();

        int getOldPeersCount();

        String getOldPeers(int index);

        java.util.List<String> getNewPeersList();

        int getNewPeersCount();

        String getNewPeers(int index);

        public interface Builder {
            Builder addOldPeers(String oldPeersId);

            Builder addNewPeers(String newPeersId);

            AddPeerResponse build();
        }
    }

    public interface RemovePeerRequest extends Message {
        String getGroupId();

        String getLeaderId();

        String getPeerId();

        interface Builder {
            Builder setGroupId(String groupId);

            Builder setLeaderId(String leaderId);

            Builder setPeerId(String peerId);

            RemovePeerRequest build();
        }

        public static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createRemovePeerRequest();
        }
    }

    public interface RemovePeerResponse extends Message {
        static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createRemovePeerResponse();
        }

        java.util.List<String> getOldPeersList();

        int getOldPeersCount();

        String getOldPeers(int index);

        java.util.List<String> getNewPeersList();

        int getNewPeersCount();

        String getNewPeers(int index);

        public interface Builder {
            Builder addOldPeers(String oldPeerId);

            Builder addNewPeers(String newPeerId);

            RemovePeerResponse build();
        }
    }

    public interface ChangePeersRequest extends Message {
        String getGroupId();

        String getLeaderId();

        java.util.List<String> getNewPeersList();

        int getNewPeersCount();

        String getNewPeers(int index);

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder setLeaderId(String leaderId);

            Builder addNewPeers(String peerId);

            ChangePeersRequest build();
        }

        public static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createChangePeerRequest();
        }
    }

    public interface ChangePeersResponse extends Message {
        static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createChangePeerResponse();
        }

        java.util.List<String> getOldPeersList();

        int getOldPeersCount();

        String getOldPeers(int index);

        java.util.List<String> getNewPeersList();

        int getNewPeersCount();

        String getNewPeers(int index);

        public interface Builder {
            Builder addOldPeers(String oldPeerId);

            Builder addNewPeers(String newPeerId);

            ChangePeersResponse build();
        }
    }

    public interface SnapshotRequest extends Message {
        String getGroupId();

        String getPeerId();

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder setPeerId(String peerId);

            SnapshotRequest build();
        }

        static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createSnapshotRequest();
        }
    }

    public interface ResetPeerRequest extends Message {
        String getGroupId();

        String getPeerId();

        java.util.List<String> getOldPeersList();

        int getOldPeersCount();

        String getOldPeers(int index);

        java.util.List<String> getNewPeersList();

        int getNewPeersCount();

        String getNewPeers(int index);

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder setPeerId(String peerId);

            Builder addNewPeers(String peerId);

            ResetPeerRequest build();
        }

        public static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createResetPeerRequest();
        }
    }

    public interface TransferLeaderRequest extends Message {
        String getGroupId();

        String getLeaderId();

        String getPeerId();

        boolean hasPeerId();

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder setLeaderId(String leaderId);

            Builder setPeerId(String peerId);

            TransferLeaderRequest build();
        }

        public static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createTransferLeaderRequest();
        }
    }

    public interface GetLeaderRequest extends Message {
        String getGroupId();

        String getPeerId();

        boolean hasPeerId();

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder setPeerId(String peerId);

            GetLeaderRequest build();
        }

        public static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createGetLeaderRequest();
        }
    }

    public interface GetLeaderResponse extends Message {
        static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createGetLeaderResponse();
        }

        String getLeaderId();

        public interface Builder {
            GetLeaderResponse build();

            Builder setLeaderId(String leaderId);
        }
    }

    public interface GetPeersRequest extends Message {
        String getGroupId();

        String getLeaderId();

        boolean getOnlyAlive();

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder setLeaderId(String leaderId);

            Builder setOnlyAlive(boolean onlyGetAlive);

            GetPeersRequest build();
        }

        public static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createGetPeersRequest();
        }
    }

    public interface GetPeersResponse extends Message {
        static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createGetPeersResponse();
        }

        java.util.List<String> getPeersList();

        int getPeersCount();

        String getPeers(int index);

        java.util.List<String> getLearnersList();

        int getLearnersCount();

        String getLearners(int index);

        public interface Builder {
            Builder addPeers(String peerId);

            Builder addLearners(String learnerId);

            GetPeersResponse build();
        }
    }

    public interface AddLearnersRequest extends Message {
        String getGroupId();

        String getLeaderId();

        java.util.List<String> getLearnersList();

        int getLearnersCount();

        String getLearners(int index);

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder setLeaderId(String leaderId);

            Builder addLearners(String learnerId);

            AddLearnersRequest build();
        }

        static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createAddLearnersRequest();
        }
    }

    public interface RemoveLearnersRequest extends Message {
        String getGroupId();

        String getLeaderId();

        java.util.List<String> getLearnersList();

        int getLearnersCount();

        String getLearners(int index);

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder setLeaderId(String leaderId);

            Builder addLearners(String leaderId);

            RemoveLearnersRequest build();
        }

        static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createRemoveLearnersRequest();
        }
    }

    public interface ResetLearnersRequest extends Message {
        String getGroupId();

        String getLeaderId();

        java.util.List<String> getLearnersList();

        /**
         * <code>repeated string learners = 3;</code>
         */
        int getLearnersCount();

        /**
         * <code>repeated string learners = 3;</code>
         */
        String getLearners(int index);

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder setLeaderId(String leaderId);

            Builder addLearners(String learnerId);

            ResetLearnersRequest build();
        }

        static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createResetLearnersRequest();
        }
    }

    public interface LearnersOpResponse extends Message {
        java.util.List<String> getOldLearnersList();

        int getOldLearnersCount();

        String getOldLearners(int index);

        java.util.List<String> getNewLearnersList();

        int getNewLearnersCount();

        String getNewLearners(int index);

        static Builder newBuilder() {
            return MessageBuilderFactory.DEFAULT.createLearnersOpResponse();
        }

        public interface Builder {
            Builder addOldLearners(String oldLearnersId);

            Builder addNewLearners(String newLearnersId);

            LearnersOpResponse build();
        }
    }
}

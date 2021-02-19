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

package org.apache.ignite.raft.client;

import java.util.List;
import org.apache.ignite.raft.PeerId;
import org.apache.ignite.raft.rpc.Message;
import org.apache.ignite.raft.rpc.RaftGroupMessage;

/**
 *
 */
public final class RaftClientCommonMessages {
    private RaftClientCommonMessages() {
    }

    public interface StatusResponse extends Message {
        int getStatusCode();

        String getStatusMsg();

        interface Builder {
            Builder setStatusCode(int code);

            Builder setStatusMsg(String msg);

            StatusResponse build();
        }
    }

    public interface PingRequest extends Message {
        long getSendTimestamp();

        interface Builder {
            Builder setSendTimestamp(long timestamp);

            PingRequest build();
        }
    }

    public interface AddPeerRequest extends RaftGroupMessage {
        PeerId getPeerId();

        interface Builder {
            Builder setGroupId(String groupId);

            Builder setPeerId(PeerId peerId);

            AddPeerRequest build();
        }
    }

    public interface AddPeerResponse extends Message {
        List<PeerId> getOldPeersList();

        List<PeerId> getNewPeersList();

        public interface Builder {
            Builder addOldPeers(PeerId oldPeersId);

            Builder addNewPeers(PeerId newPeersId);

            AddPeerResponse build();
        }
    }

    public interface RemovePeerRequest extends RaftGroupMessage {
        PeerId getPeerId();

        interface Builder {
            Builder setGroupId(String groupId);

            Builder setPeerId(PeerId peerId);

            RemovePeerRequest build();
        }
    }

    public interface RemovePeerResponse extends Message {
        List<PeerId> getOldPeersList();

        List<PeerId> getNewPeersList();

        public interface Builder {
            Builder addOldPeers(PeerId oldPeerId);

            Builder addNewPeers(PeerId newPeerId);

            RemovePeerResponse build();
        }
    }

    public interface ChangePeersRequest extends RaftGroupMessage {
        List<PeerId> getNewPeersList();

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder addNewPeers(PeerId peerId);

            ChangePeersRequest build();
        }
    }

    public interface ChangePeersResponse extends Message {
        List<PeerId> getOldPeersList();

        List<PeerId> getNewPeersList();

        public interface Builder {
            Builder addOldPeers(PeerId oldPeerId);

            Builder addNewPeers(PeerId newPeerId);

            ChangePeersResponse build();
        }
    }

    public interface SnapshotRequest extends RaftGroupMessage {
        public interface Builder {
            Builder setGroupId(String groupId);

            SnapshotRequest build();
        }
    }

    public interface ResetPeerRequest extends RaftGroupMessage {
        List<PeerId> getNewPeersList();

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder addNewPeers(PeerId peerId);

            ResetPeerRequest build();
        }
    }

    public interface TransferLeaderRequest extends RaftGroupMessage {
        PeerId getPeerId();

        public interface Builder {
            Builder setGroupId(String groupId);

            TransferLeaderRequest build();
        }
    }

    public interface GetLeaderRequest extends RaftGroupMessage {
        public interface Builder {
            Builder setGroupId(String groupId);

            GetLeaderRequest build();
        }
    }

    public interface GetLeaderResponse extends Message {
        PeerId getLeaderId();

        public interface Builder {
            GetLeaderResponse build();

            Builder setLeaderId(PeerId leaderId);
        }
    }

    public interface GetPeersRequest extends RaftGroupMessage {
        boolean getOnlyAlive();

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder setOnlyAlive(boolean onlyGetAlive);

            GetPeersRequest build();
        }
    }

    public interface GetPeersResponse extends Message {
        List<PeerId> getPeersList();

        List<PeerId> getLearnersList();

        public interface Builder {
            Builder addPeers(PeerId peerId);

            Builder addLearners(PeerId learnerId);

            GetPeersResponse build();
        }
    }

    public interface AddLearnersRequest extends RaftGroupMessage {
        List<PeerId> getLearnersList();

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder addLearners(PeerId learnerId);

            AddLearnersRequest build();
        }
    }

    public interface RemoveLearnersRequest extends RaftGroupMessage {
        List<PeerId> getLearnersList();

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder addLearners(PeerId leaderId);

            RemoveLearnersRequest build();
        }
    }

    public interface ResetLearnersRequest extends RaftGroupMessage {
        List<PeerId> getLearnersList();

        public interface Builder {
            Builder setGroupId(String groupId);

            Builder addLearners(PeerId learnerId);

            ResetLearnersRequest build();
        }
    }

    public interface LearnersOpResponse extends Message {
        List<PeerId> getOldLearnersList();

        List<PeerId> getNewLearnersList();

        public interface Builder {
            Builder addOldLearners(PeerId oldLearnersId);

            Builder addNewLearners(PeerId newLearnersId);

            LearnersOpResponse build();
        }
    }

    public interface UserRequest<T> extends Message {
        T request();

        public interface Builder<T> {
            Builder setRequest(T request);

            UserRequest<T> build();
        }
    }

    public interface UserResponse<T> extends Message {
        T response();

        public interface Builder<T> {
            Builder setResponse(T response);

            UserResponse<T> build();
        }
    }
}
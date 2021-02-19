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
package org.apache.ignite.raft.client.rpc;

import java.util.concurrent.CompletableFuture;
import org.apache.ignite.raft.State;
import org.apache.ignite.raft.PeerId;
import org.apache.ignite.raft.rpc.Message;
import org.apache.ignite.raft.rpc.RaftGroupMessage;
import org.jetbrains.annotations.Nullable;

import static org.apache.ignite.raft.client.RaftClientCommonMessages.AddLearnersRequest;
import static org.apache.ignite.raft.client.RaftClientCommonMessages.AddPeerRequest;
import static org.apache.ignite.raft.client.RaftClientCommonMessages.AddPeerResponse;
import static org.apache.ignite.raft.client.RaftClientCommonMessages.ChangePeersRequest;
import static org.apache.ignite.raft.client.RaftClientCommonMessages.ChangePeersResponse;
import static org.apache.ignite.raft.client.RaftClientCommonMessages.LearnersOpResponse;
import static org.apache.ignite.raft.client.RaftClientCommonMessages.RemoveLearnersRequest;
import static org.apache.ignite.raft.client.RaftClientCommonMessages.RemovePeerRequest;
import static org.apache.ignite.raft.client.RaftClientCommonMessages.RemovePeerResponse;
import static org.apache.ignite.raft.client.RaftClientCommonMessages.ResetLearnersRequest;
import static org.apache.ignite.raft.client.RaftClientCommonMessages.ResetPeerRequest;
import static org.apache.ignite.raft.client.RaftClientCommonMessages.SnapshotRequest;
import static org.apache.ignite.raft.client.RaftClientCommonMessages.StatusResponse;
import static org.apache.ignite.raft.client.RaftClientCommonMessages.TransferLeaderRequest;

/**
 * Low-level raft group RPC client.
 * <p>
 * Additionally maintains raft group state.
 */
public interface RaftGroupRpcClient {
    /**
     * @param groupId Group id.
     * @return Current group state or null if state is not yet initalized.
     */
    @Nullable State state(String groupId);

    /**
     * Refreshes a state of initialized group.
     * @param groupId Group id.
     * @return A future.
     */
    CompletableFuture<PeerId> refreshLeader(String groupId);

    /**
     * Adds a voring peer to the raft group.
     *
     * @param request   request data
     * @return A future with the result
     */
    CompletableFuture<AddPeerResponse> addPeer(AddPeerRequest request);

    /**
     * Removes a peer from the raft group.
     *
     * @param endpoint  server address
     * @param request   request data
     * @return a future with result
     */
    CompletableFuture<RemovePeerResponse> removePeer(RemovePeerRequest request);

    /**
     * Locally resets raft group peers. Intended for recovering from a group unavailability at the price of consistency.
     *
     * @param peerId Node to execute the configuration reset.
     * @param request   request data
     * @return A future with result.
     */
    CompletableFuture<StatusResponse> resetPeers(PeerId peerId, ResetPeerRequest request);

    /**
     * Takes a local snapshot.
     *
     * @param peerId  Peer id.
     * @param request   request data
     * @param done      callback
     * @return a future with result
     */
    CompletableFuture<StatusResponse> snapshot(PeerId peerId, SnapshotRequest request);

    /**
     * Change peers.
     *
     * @param endpoint  server address
     * @param request   request data
     * @param done      callback
     * @return a future with result
     */
    CompletableFuture<ChangePeersResponse> changePeers(ChangePeersRequest request);

    /**
     * Adds learners.
     *
     * @param endpoint  server address
     * @param request   request data
     * @param done      callback
     * @return a future with result
     */
    CompletableFuture<LearnersOpResponse> addLearners(AddLearnersRequest request);

    /**
     * Removes learners.
     *
     * @param endpoint  server address
     * @param request   request data
     * @param done      callback
     * @return a future with result
     */
    CompletableFuture<LearnersOpResponse> removeLearners(RemoveLearnersRequest request);

    /**
     * Resets learners to new set.
     *
     * @param endpoint  server address
     * @param request   request data
     * @param done      callback
     * @return a future with result
     */
    CompletableFuture<LearnersOpResponse> resetLearners(ResetLearnersRequest request);

    /**
     * Transfer leadership to other peer.
     *
     * @param endpoint  server address
     * @param request   request data
     * @param done      callback
     * @return a future with result
     */
    CompletableFuture<StatusResponse> transferLeader(TransferLeaderRequest request);

    /**
     * Performs a custom action defined by specific request on the raft group leader.
     *
     * @param endpoint  server address
     * @param request   request data
     * @param done      callback
     * @return a future with result
     */
    <R extends Message> CompletableFuture<R> sendCustom(RaftGroupMessage request);
}
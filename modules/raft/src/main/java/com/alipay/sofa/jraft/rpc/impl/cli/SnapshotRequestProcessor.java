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
package com.alipay.sofa.jraft.rpc.impl.cli;

import java.util.concurrent.Executor;

import com.alipay.sofa.jraft.rpc.CliRequests.SnapshotRequest;
import com.alipay.sofa.jraft.rpc.RpcRequestClosure;
import com.alipay.sofa.jraft.rpc.RpcRequests;
import com.alipay.sofa.jraft.rpc.Message;

/**
 * Snapshot request processor.
 *
 * @author boyan (boyan@alibaba-inc.com)
 * @author jiachun.fjc
 */
public class SnapshotRequestProcessor extends BaseCliRequestProcessor<SnapshotRequest> {

    public SnapshotRequestProcessor(Executor executor) {
        super(executor, RpcRequests.ErrorResponse.getDefaultInstance());
    }

    @Override
    protected String getPeerId(final SnapshotRequest request) {
        return request.getPeerId();
    }

    @Override
    protected String getGroupId(final SnapshotRequest request) {
        return request.getGroupId();
    }

    @Override
    protected Message processRequest0(final CliRequestContext ctx, final SnapshotRequest request,
                                      final RpcRequestClosure done) {
        LOG.info("Receive SnapshotRequest to {} from {}", ctx.node.getNodeId(), request.getPeerId());
        ctx.node.snapshot(done);
        return null;
    }

    @Override
    public String interest() {
        return SnapshotRequest.class.getName();
    }

}

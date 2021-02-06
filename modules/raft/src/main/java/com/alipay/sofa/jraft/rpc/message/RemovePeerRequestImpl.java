package com.alipay.sofa.jraft.rpc.message;

import com.alipay.sofa.jraft.rpc.CliRequests;

public class RemovePeerRequestImpl implements CliRequests.RemovePeerRequest, CliRequests.RemovePeerRequest.Builder {
    private String groupId;
    private String leaderId;
    private String peerId;

    @Override public String getGroupId() {
        return groupId;
    }

    @Override public String getLeaderId() {
        return leaderId;
    }

    @Override public String getPeerId() {
        return peerId;
    }

    @Override public Builder setGroupId(String groupId) {
        this.groupId = groupId;

        return this;
    }

    @Override public Builder setLeaderId(String leaderId) {
        this.leaderId = leaderId;

        return this;
    }

    @Override public Builder setPeerId(String peerId) {
        this.peerId = peerId;

        return this;
    }

    @Override public CliRequests.RemovePeerRequest build() {
        return this;
    }
}
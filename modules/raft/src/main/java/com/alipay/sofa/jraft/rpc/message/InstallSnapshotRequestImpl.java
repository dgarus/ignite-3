package com.alipay.sofa.jraft.rpc.message;

import com.alipay.sofa.jraft.entity.RaftOutter;
import com.alipay.sofa.jraft.rpc.RpcRequests;

public class InstallSnapshotRequestImpl implements RpcRequests.InstallSnapshotRequest, RpcRequests.InstallSnapshotRequest.Builder {
    private String groupId;
    private String serverId;
    private String peerId;
    private long term;
    private RaftOutter.SnapshotMeta meta;
    private String uri;

    @Override public String getGroupId() {
        return groupId;
    }

    @Override public String getServerId() {
        return serverId;
    }

    @Override public String getPeerId() {
        return peerId;
    }

    @Override public long getTerm() {
        return term;
    }

    @Override public RaftOutter.SnapshotMeta getMeta() {
        return meta;
    }

    @Override public String getUri() {
        return uri;
    }

    @Override public RpcRequests.InstallSnapshotRequest build() {
        return this;
    }

    @Override public Builder setTerm(long term) {
        this.term = term;

        return this;
    }

    @Override public Builder setGroupId(String groupId) {
        this.groupId = groupId;

        return this;
    }

    @Override public Builder setServerId(String serverId) {
        this.serverId = serverId;

        return this;
    }

    @Override public Builder setPeerId(String peerId) {
        this.peerId = peerId;

        return this;
    }

    @Override public Builder setMeta(RaftOutter.SnapshotMeta meta) {
        this.meta = meta;

        return this;
    }

    @Override public Builder setUri(String uri) {
        this.uri = uri;

        return this;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstallSnapshotRequestImpl that = (InstallSnapshotRequestImpl) o;

        if (term != that.term) return false;
        if (!groupId.equals(that.groupId)) return false;
        if (!serverId.equals(that.serverId)) return false;
        if (!peerId.equals(that.peerId)) return false;
        if (!meta.equals(that.meta)) return false;
        return uri.equals(that.uri);
    }

    @Override public int hashCode() {
        int result = groupId.hashCode();
        result = 31 * result + serverId.hashCode();
        result = 31 * result + peerId.hashCode();
        result = 31 * result + (int) (term ^ (term >>> 32));
        result = 31 * result + meta.hashCode();
        result = 31 * result + uri.hashCode();
        return result;
    }
}

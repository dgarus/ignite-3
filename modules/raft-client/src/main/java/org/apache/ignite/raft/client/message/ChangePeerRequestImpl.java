package org.apache.ignite.raft.client.message;

import java.util.ArrayList;
import java.util.List;
import org.apache.ignite.raft.PeerId;
import org.apache.ignite.raft.client.RaftClientCommonMessages;

class ChangePeerRequestImpl implements RaftClientCommonMessages.ChangePeersRequest, RaftClientCommonMessages.ChangePeersRequest.Builder {
    private String groupId;
    private List<PeerId> newPeersList = new ArrayList<>();

    @Override public String getGroupId() {
        return groupId;
    }

    @Override public List<PeerId> getNewPeersList() {
        return newPeersList;
    }

    @Override public Builder setGroupId(String groupId) {
        this.groupId = groupId;

        return this;
    }

    @Override public Builder addNewPeers(PeerId peerId) {
        newPeersList.add(peerId);

        return this;
    }

    @Override public RaftClientCommonMessages.ChangePeersRequest build() {
        return this;
    }
}
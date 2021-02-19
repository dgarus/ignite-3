package org.apache.ignite.raft.client.message;

import java.util.ArrayList;
import java.util.List;
import org.apache.ignite.raft.PeerId;
import org.apache.ignite.raft.client.RaftClientCommonMessages;

class AddPeerResponseImpl implements RaftClientCommonMessages.AddPeerResponse, RaftClientCommonMessages.AddPeerResponse.Builder {
    private List<PeerId> oldPeersList = new ArrayList<>();
    private List<PeerId> newPeersList = new ArrayList<>();

    @Override public List<PeerId> getOldPeersList() {
        return oldPeersList;
    }

    @Override public List<PeerId> getNewPeersList() {
        return newPeersList;
    }

    @Override public Builder addOldPeers(PeerId oldPeersId) {
        oldPeersList.add(oldPeersId);

        return this;
    }

    @Override public Builder addNewPeers(PeerId newPeersId) {
        newPeersList.add(newPeersId);

        return this;
    }

    @Override public RaftClientCommonMessages.AddPeerResponse build() {
        return this;
    }
}
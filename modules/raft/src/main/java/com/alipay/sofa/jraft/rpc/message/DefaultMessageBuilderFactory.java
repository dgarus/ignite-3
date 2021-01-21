package com.alipay.sofa.jraft.rpc.message;

import com.alipay.sofa.jraft.entity.LocalFileMetaOutter;
import com.alipay.sofa.jraft.entity.LocalStorageOutter;
import com.alipay.sofa.jraft.entity.RaftOutter;
import com.alipay.sofa.jraft.rpc.CliRequests;
import com.alipay.sofa.jraft.rpc.MessageBuilderFactory;
import com.alipay.sofa.jraft.rpc.RpcRequests;

public class DefaultMessageBuilderFactory implements MessageBuilderFactory {
    @Override public LocalFileMetaOutter.LocalFileMeta.Builder createLocalFileMeta() {
        return new LocalFileMetaImpl();
    }

    @Override public RpcRequests.PingRequest.Builder createPingRequest() {
        return new PingRequestImpl();
    }

    @Override public RpcRequests.RequestVoteRequest.Builder createVoteRequest() {
        return new PreVoteRequestImpl();
    }

    @Override public RpcRequests.RequestVoteResponse.Builder createVoteResponse() {
        return new RequestVoteResponseImpl();
    }

    @Override public RpcRequests.ErrorResponse.Builder createErrorResponse() {
        return new ErrorResponseImpl();
    }

    @Override public LocalStorageOutter.StablePBMeta.Builder createStableMeta() {
        return new StableMeta();
    }

    @Override public RpcRequests.AppendEntriesRequest.Builder createAppendEntriesRequest() {
        return new AppendEntriesRequestImpl();
    }

    @Override public RpcRequests.AppendEntriesResponse.Builder createAppendEntriesResponse() {
        return new AppendEntriesResponseImpl();
    }

    @Override public RaftOutter.EntryMeta.Builder createEntryMeta() {
        return new EntryMetaImpl();
    }

    @Override public RpcRequests.TimeoutNowRequest.Builder createTimeoutNowRequest() {
        return new TimeoutNowRequestImpl();
    }

    @Override public RpcRequests.TimeoutNowResponse.Builder createTimeoutNowResponse() {
        return new TimeoutNowResponseImpl();
    }

    @Override public RpcRequests.ReadIndexRequest.Builder createReadIndexRequest() {
        return new ReadIndexRequestImpl();
    }

    @Override public RpcRequests.ReadIndexResponse.Builder createReadIndexResponse() {
        return new ReadIndexResponseImpl();
    }

    @Override public RaftOutter.SnapshotMeta.Builder createSnapshotMeta() {
        return new SnapshotMetaImpl();
    }

    @Override public LocalStorageOutter.LocalSnapshotPbMeta.Builder createLocalSnapshotMeta() {
        return new LocalSnapshotMetaImpl();
    }

    @Override public LocalStorageOutter.LocalSnapshotPbMeta.File.Builder createFile() {
        return new LocalSnapshotMetaFileImpl();
    }

    @Override public RpcRequests.InstallSnapshotRequest.Builder createInstallSnapshotRequest() {
        return new InstallSnapshotRequestImpl();
    }

    @Override public RpcRequests.InstallSnapshotResponse.Builder createInstallSnapshotResponse() {
        return new InstallSnapshotResponseImpl();
    }

    @Override public RpcRequests.GetFileRequest.Builder createGetFileRequest() {
        return new GetFileRequestImpl();
    }

    @Override public RpcRequests.GetFileResponse.Builder createGetFileResponse() {
        return new GetFileResponseImpl();
    }

    @Override public CliRequests.AddPeerRequest.Builder createAddPeerRequest() {
        return new AddPeerRequestImpl();
    }

    @Override public CliRequests.AddPeerResponse.Builder createAddPeerResponse() {
        return null;
    }

    @Override public CliRequests.RemovePeerRequest.Builder createRemovePeerRequest() {
        return null;
    }

    @Override public CliRequests.RemovePeerResponse.Builder createRemovePeerResponse() {
        return null;
    }

    @Override public CliRequests.ChangePeersRequest.Builder createChangePeerRequest() {
        return null;
    }

    @Override public CliRequests.ChangePeersResponse.Builder createChangePeerResponse() {
        return null;
    }

    @Override public CliRequests.SnapshotRequest.Builder createSnapshotRequest() {
        return null;
    }

    @Override public CliRequests.ResetPeerRequest.Builder createResetPeerRequest() {
        return null;
    }

    @Override public CliRequests.TransferLeaderRequest.Builder createTransferLeaderRequest() {
        return null;
    }

    @Override public CliRequests.GetLeaderRequest.Builder createGetLeaderRequest() {
        return new CreateGetLeaderRequestImpl();
    }

    @Override public CliRequests.GetLeaderResponse.Builder createGetLeaderResponse() {
        return new CreateGetLeaderResponseImpl();
    }

    @Override public CliRequests.GetPeersRequest.Builder createGetPeersRequest() {
        return null;
    }

    @Override public CliRequests.GetPeersResponse.Builder createGetPeersResponse() {
        return null;
    }

    @Override public CliRequests.AddLearnersRequest.Builder createAddLearnersRequest() {
        return null;
    }

    @Override public CliRequests.RemoveLearnersRequest.Builder createRemoveLearnersRequest() {
        return null;
    }

    @Override public CliRequests.ResetLearnersRequest.Builder createResetLearnersRequest() {
        return null;
    }

    @Override public CliRequests.LearnersOpResponse.Builder createLearnersOpResponse() {
        return null;
    }
}

package com.alipay.sofa.jraft.counter;

import com.alipay.sofa.jraft.NodeManager;
import com.alipay.sofa.jraft.RouteTable;
import com.alipay.sofa.jraft.conf.Configuration;
import com.alipay.sofa.jraft.core.NodeImpl;
import com.alipay.sofa.jraft.core.NodeTest;
import com.alipay.sofa.jraft.core.TestCluster;
import com.alipay.sofa.jraft.counter.rpc.IncrementAndGetRequest;
import com.alipay.sofa.jraft.entity.PeerId;
import com.alipay.sofa.jraft.error.RemotingException;
import com.alipay.sofa.jraft.option.CliOptions;
import com.alipay.sofa.jraft.rpc.InvokeCallback;
import com.alipay.sofa.jraft.rpc.impl.cli.CliClientServiceImpl;
import com.alipay.sofa.jraft.test.TestUtils;
import com.alipay.sofa.jraft.util.Utils;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeoutException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CounterExampleTest {
    static final Logger LOG            = LoggerFactory.getLogger(NodeTest.class);
    @Rule
    public TestName testName       = new TestName();
    private String              dataPath;

    @Before
    public void setup() throws Exception {
        System.out.println(">>>>>>>>>>>>>>> Start test method: " + this.testName.getMethodName());
        this.dataPath = TestUtils.mkTempDir();
        new File(this.dataPath).mkdirs();
        assertEquals(NodeImpl.GLOBAL_NUM_NODES.get(), 0);
    }

    @After
    public void teardown() throws Exception {
        if (NodeImpl.GLOBAL_NUM_NODES.get() > 0) {
            Thread.sleep(5000);
            assertEquals(0, NodeImpl.GLOBAL_NUM_NODES.get());
        }
        assertTrue(Utils.delete(new File(this.dataPath)));
        NodeManager.getInstance().clear();

        System.out.println(">>>>>>>>>>>>>>> End test method: " + this.testName.getMethodName());
    }

    @Test
    public void testCounter() throws IOException, InterruptedException, TimeoutException, RemotingException {

        try {
            String initConfStr = "127.0.0.1:8080,127.0.0.1:8081,127.0.0.1:8082";

            String groupId = "counter";

            // Create initial topology.
            CounterServer node0 = CounterServer.start(dataPath, groupId, "127.0.0.1:8080", initConfStr);
            CounterServer node1 = CounterServer.start(dataPath, groupId, "127.0.0.1:8081", initConfStr);
            CounterServer node2 = CounterServer.start(dataPath, groupId, "127.0.0.1:8082", initConfStr);

            Thread.sleep(3000);

            // Create client.
            final Configuration conf = new Configuration();

            assertTrue(conf.parse(initConfStr));

            RouteTable.getInstance().updateConfiguration(groupId, conf);

            final CliClientServiceImpl cliClientService = new CliClientServiceImpl();
            cliClientService.init(new CliOptions());

            if (!RouteTable.getInstance().refreshLeader(cliClientService, groupId, 1000).isOk()) {
                throw new IllegalStateException("Refresh leader failed");
            }

            final PeerId leader = RouteTable.getInstance().selectLeader(groupId);
            System.out.println("Leader is " + leader);
            final int n = 1;
            final CountDownLatch latch = new CountDownLatch(n);
            final long start = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                incrementAndGet(cliClientService, leader, i, latch);
            }
            latch.await();
            System.out.println(n + " ops, cost : " + (System.currentTimeMillis() - start) + " ms.");
        }
        finally {
            CounterServer.stopAll();
        }
    }

    private static void incrementAndGet(final CliClientServiceImpl cliClientService, final PeerId leader,
                                        final long delta, CountDownLatch latch) throws RemotingException, InterruptedException {
        final IncrementAndGetRequest request = new IncrementAndGetRequest();
        request.setDelta(delta);

        cliClientService.getRpcClient().invokeAsync(leader.getEndpoint(), request, new InvokeCallback() {

            @Override
            public void complete(Object result, Throwable err) {
                if (err == null) {
                    latch.countDown();
                    System.out.println("incrementAndGet result:" + result);
                } else {
                    err.printStackTrace();
                    latch.countDown();
                }
            }

            @Override
            public Executor executor() {
                return null;
            }
        }, 5000);
    }
}

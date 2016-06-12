package xyz.yakdmt.followmeradio.data;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import xyz.yakdmt.followmeradio.network.NetworkWorker;

/**
 * Created by yakdmt on 11/06/16.
 */
public class DataManager {

    private final NetworkWorker mNetworkWorker;
    private final ScheduledExecutorService;

    @Inject
    DataManager(NetworkWorker networkWorker) {
        mNetworkWorker = networkWorker;
        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate
                ((Runnable) () -> {
                    // call service
                }, 0, 10, TimeUnit.SECONDS);
    }


}

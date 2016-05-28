package xyz.yakdmt.followmeradio.data;

import javax.inject.Inject;

import xyz.yakdmt.followmeradio.network.NetworkService;

/**
 * Created by yakdmt on 20/04/16.
 */
public class DataManager {

    private final NetworkService mNetworkService;

    @Inject
    public DataManager(NetworkService networkService) {
        mNetworkService = networkService;
    }

}

package xyz.yakdmt.followmeradio.ui.broadcast;

import javax.inject.Inject;

import xyz.yakdmt.followmeradio.playback.PlaybackManager;
import xyz.yakdmt.followmeradio.ui.base.BasePresenter;

/**
 * Created by yakdmt on 21/04/16.
 */
public class BroadcastPresenter extends BasePresenter<BroadcastMvpView> {

    private final PlaybackManager mPlaybackManager;

    @Inject
    public BroadcastPresenter(PlaybackManager playbackManager) {
        mPlaybackManager = playbackManager;
    }

}

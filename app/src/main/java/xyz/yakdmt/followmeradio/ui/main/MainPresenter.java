package xyz.yakdmt.followmeradio.ui.main;

import javax.inject.Inject;

import rx.Subscription;
import xyz.yakdmt.followmeradio.playback.PlaybackManager;
import xyz.yakdmt.followmeradio.ui.base.BasePresenter;

/**
 * Created by yakdmt on 20/04/16.
 */
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final PlaybackManager mPlaybackManager;
    private Subscription mSubscription;

    @Inject
    public MainPresenter(PlaybackManager playbackManager) {
        mPlaybackManager = playbackManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void onFabClick(){
        mPlaybackManager.togglePlayback();
    }

}

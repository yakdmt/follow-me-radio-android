package xyz.yakdmt.followmeradio.ui.main;

import javax.inject.Inject;

import rx.Subscription;
import xyz.yakdmt.followmeradio.data.DataManager;
import xyz.yakdmt.followmeradio.playback.PlaybackManager;
import xyz.yakdmt.followmeradio.ui.base.BasePresenter;

/**
 * Created by yakdmt on 20/04/16.
 */
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;
    private final PlaybackManager mPlaybackManager;
    private Subscription mSubscription;

    @Inject
    public MainPresenter(DataManager dataManager, PlaybackManager playbackManager) {
        mDataManager = dataManager;
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

    public void play() {
        mPlaybackManager.play();
    }

}

package xyz.yakdmt.followmeradio.playback;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import xyz.yakdmt.followmeradio.R;
import xyz.yakdmt.followmeradio.injection.ApplicationContext;
import xyz.yakdmt.followmeradio.ui.main.MainActivity;
import xyz.yakdmt.followmeradio.utils.Constants;

/**
 * Created by yakdmt on 21/04/16.
 */
public class PlaybackManager {

    private final AudioService mAudioService;
    private final Context mContext;
    public static String[] BITRATES = {Constants.BITRATE_32, Constants.BITRATE_64, Constants.BITRATE_128, Constants.BITRATE_192};
    private String mCurrentQuality = Constants.BITRATE_192;
    private String mCurrentStream = Constants.STREAM_NEW;

    @Inject
    public PlaybackManager(AudioService audioService, @ApplicationContext Context context) {
        mAudioService = audioService;
        mContext = context;
    }

    public void play(){
        String url = Constants.BASE_URL + getCurrentStream() + getCurrentQuality();
        Intent startIntent = new Intent(mContext, AudioService.class);
        startIntent.setAction(Constants.ACTION_PLAY);
        startIntent.putExtra(Constants.AUDIO_TYPE, Constants.STREAM);
        startIntent.putExtra(Constants.STREAM_URL, url);
        mContext.startService(startIntent);
    }

    public void stop(){
        Intent pauseIntent = new Intent(mContext, AudioService.class);
        pauseIntent.setAction(Constants.ACTION_PAUSE);
        mContext.startService(pauseIntent);
    }

    @Constants.Quality
    public String getCurrentQuality() {
        return mCurrentQuality;
    }
    public void setQuality(@Constants.Quality String quality) {
        mCurrentQuality = quality;
        play();
    }

    @Constants.Stream
    public String getCurrentStream() {
        return mCurrentStream;
    }

}

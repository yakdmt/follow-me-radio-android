package xyz.yakdmt.followmeradio.playback;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import javax.inject.Inject;

import xyz.yakdmt.followmeradio.injection.ApplicationContext;
import xyz.yakdmt.followmeradio.utils.Constants;

/**
 * Created by yakdmt on 21/04/16.
 */
public class PlaybackManager {

    private AudioService mAudioService;
    private AudioService.AudioBinder mBinder;
    private final Context mContext;
    protected ServiceConnection mServerConnection;
    public static String[] BITRATES = {Constants.BITRATE_32, Constants.BITRATE_64, Constants.BITRATE_128, Constants.BITRATE_192};
    private String mCurrentQuality = Constants.BITRATE_192;
    private String mCurrentStream = Constants.STREAM_NEW;
    private boolean isNeedStopPlayback = false;


    @Inject
    public PlaybackManager(AudioService audioService, @ApplicationContext Context context) {
        mAudioService = audioService;
        mContext = context;
        mServerConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                mBinder = (AudioService.AudioBinder)binder;
                if (isNeedStopPlayback) {
                    mBinder.startPlayback(getStopIntent());
                    isNeedStopPlayback = false;
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mBinder = null;
            }
        };
    }

    public Intent getCurrentPlayIntent(){
        String url = Constants.BASE_URL + getCurrentStream() + getCurrentQuality();
        Intent startIntent = new Intent(mContext, mAudioService.getClass());
        startIntent.setAction(Constants.ACTION_PLAY);
        startIntent.putExtra(Constants.AUDIO_TYPE, Constants.STREAM);
        startIntent.putExtra(Constants.STREAM_URL, url);
        return startIntent;
    }

    public Intent getBindIntent(){
        Intent startIntent = new Intent(mContext, mAudioService.getClass());
        return startIntent;
    }

    public Intent getStopIntent(){
        Intent stopIntent = new Intent(mContext, mAudioService.getClass());
        stopIntent.setAction(Constants.ACTION_STOP);
        return stopIntent;
    }


    @Constants.Quality
    public String getCurrentQuality() {
        return mCurrentQuality;
    }
    public void setQuality(@Constants.Quality String quality) {
        mCurrentQuality = quality;
    }

    @Constants.Stream
    public String getCurrentStream() {
        return mCurrentStream;
    }

    public void togglePlayback(){
        if (!AudioService.isWorking()) {
            mContext.startService(getCurrentPlayIntent());
            doBindService(getBindIntent());
            return;
        }
        if (!isServiceBound()) {
            isNeedStopPlayback = true;
            mContext.bindService(getStopIntent(), mServerConnection, 0);
            return;
        }
        if (mBinder.isPlaying()) {
            mBinder.stopPlayback();
        } else {
            mBinder.startPlayback(getCurrentPlayIntent());
        }
    }

    public void doBindService(Intent intent) {
        mContext.bindService(intent, mServerConnection, 0);
    }

    private boolean isServiceBound(){
        return mBinder != null;
    }


}

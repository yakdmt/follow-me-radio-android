package xyz.yakdmt.followmeradio.playback;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import xyz.yakdmt.followmeradio.ui.base.BaseActivity;
import xyz.yakdmt.followmeradio.utils.Constants;

/**
 * Created by yakdmt on 20/04/16.
 */
public class AudioServiceImpl extends IntentService implements AudioService, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener  {


    private static final String EXTRA_TYPE = "audio_type";

    MediaPlayer mMediaPlayer = null;
    private String mLastAction = "";
    private int mCurrentType;
    private String mCurrentUrl;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public AudioServiceImpl(String name) {
        super(name);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("service", "onStartCommand");
        mLastAction = intent.getAction();
        if (intent.getAction().equals(Constants.ACTION_PLAY)) {
            Log.i("service", "ACTION_PLAY");
            if (intent.getExtras()!=null && intent.getExtras().getInt(Constants.AUDIO_TYPE)==Constants.STREAM) {
                mCurrentType = Constants.STREAM;
                mCurrentUrl = intent.getExtras().getString(Constants.STREAM_URL);
            }
            mMediaPlayer = new MediaPlayer();
            try {
                mMediaPlayer.setDataSource(mCurrentUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mMediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
            WifiManager.WifiLock wifiLock = ((WifiManager) getSystemService(Context.WIFI_SERVICE))
                    .createWifiLock(WifiManager.WIFI_MODE_FULL, "audiolock");

            wifiLock.acquire();
            mMediaPlayer.setOnErrorListener(this);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.prepareAsync(); // prepare async to not block main thread
        } else if (intent.getAction().equals(
                Constants.ACTION_PAUSE)) {
            Log.i("service", "ACTION_PAUSE");
            if (mMediaPlayer!=null) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                stopForeground(true);
                stopSelf();
            }
        }
        String songName;
// assign the song name to songName
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0,
                new Intent(getApplicationContext(), BaseActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("Audio")
                .setContentText("playing")
                .build(); // available from API level 11 and onwards
        notification.tickerText = "text";
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        startForeground(1111, notification);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void playBroadcast() {

    }

    @Override
    public void stopBroadcast() {

    }
}


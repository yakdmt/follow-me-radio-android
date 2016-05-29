package xyz.yakdmt.followmeradio.playback;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import java.io.IOException;

import xyz.yakdmt.followmeradio.FollowMeApplication;
import xyz.yakdmt.followmeradio.ui.main.MainActivity;
import xyz.yakdmt.followmeradio.utils.Constants;

/**
 * Created by yakdmt on 20/04/16.
 */
public class AudioService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener  {

    private final IBinder mBinder = new AudioBinder();
    private static boolean isWorking = false;
    private static final String EXTRA_TYPE = "audio_type";

    MediaPlayer mMediaPlayer;
    WifiManager.WifiLock mWifiLock;
    private String mLastAction = "";
    private int mCurrentType = Constants.STREAM;
    private String mCurrentUrl = Constants.BASE_URL + Constants.STREAM_NEW + Constants.BITRATE_192;

    public AudioService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FollowMeApplication.get(this).getComponent().inject(this);
        isWorking = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isWorking = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
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
            mWifiLock = ((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE))
                    .createWifiLock(WifiManager.WIFI_MODE_FULL, "audiolock");

            mWifiLock.acquire();
            mMediaPlayer.setOnErrorListener(this);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.prepareAsync(); // prepare async to not block main thread
        } else if (intent.getAction().equals(
                Constants.ACTION_STOP)) {
            if (mMediaPlayer!=null) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                stopForeground(true);
                stopSelf();
            }
        }
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0,
                new Intent(getApplicationContext(), MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("Audio")
                .setContentText("playing")
                .setContentIntent(pi)
                .build();
        notification.tickerText = "text";
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        startForeground(1111, notification);
        return START_NOT_STICKY;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    public void playBroadcast(Intent intent) {
        getApplicationContext().startService(intent);
        //onStartCommand(intent);
    }

    public void stopBroadcast() {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_STOP);
        //onStartCommand(intent);
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, AudioService.class);
    }


    public class AudioBinder extends Binder {
        AudioService getService() {
            return AudioService.this;
        }

        public boolean isPlaying(){
            if (mMediaPlayer==null) {
                return false;
            }
            boolean isPlaying = false;
            try {
                isPlaying = mMediaPlayer.isPlaying();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            return isPlaying;
        }

        public void stopPlayback() {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mWifiLock.release();
            stopForeground(true);
        }

        public void startPlayback(Intent intent) {
           onStartCommand(intent, 0,0);
        }

    }

    public static boolean isWorking(){
        return isWorking;
    }





}


package xyz.yakdmt.followmeradio.utils;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by yakdmt on 20/04/16.
 */
public class Constants {
    public static final String BASE_URL = "http://radiofollow.me";
    public static final String BASE_STREAM_URL = "http://m.radiofollow.me";
    public static final String STREAM_NEW = ":8000";
    public static final String STREAM_HITS = ":8001";
    public static final String STREAM_RUS = ":8002";
    public static final String STREAM_MIXES = ":8004";
    public static final String BITRATE_32 = "/live32";
    public static final String BITRATE_64 = "/live64";
    public static final String BITRATE_128 = "/live128";
    public static final String BITRATE_192 = "/live192";

    public static final String ACTION_PLAY = "com.yakdmt.action.PLAY";
    public static final String ACTION_PAUSE = "com.yakdmt.action.PAUSE";
    public static final String ACTION_INIT = "com.yakdmt.action.INIT";

    public static final String AUDIO_TYPE = "audio_type";
    public static final int STREAM = 0;
    public static final int PODCAST_ONLINE = 1;
    public static final int PODCAST_LOADED = 2;

    public static final String STREAM_URL = "stream_url";

    @StringDef({STREAM_NEW, STREAM_HITS, STREAM_RUS, STREAM_MIXES})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Stream {}

    @StringDef({BITRATE_32, BITRATE_64, BITRATE_128, BITRATE_192})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Quality {}



}
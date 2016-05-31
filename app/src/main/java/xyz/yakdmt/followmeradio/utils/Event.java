package xyz.yakdmt.followmeradio.utils;

/**
 * Created by yakdmt on 29/05/16.
 */
public class Event {

    public static class OnAudioStateChanded {

        public static final int STATE_IDLE = 0;
        public static final int STATE_PLAYING = 1;

        private int mState;

        public OnAudioStateChanded(int state) {
            mState = state;
        }

        public int getState(){
            return mState;
        }
    }
}

package xyz.yakdmt.followmeradio.playback;

/**
 * Created by yakdmt on 20/04/16.
 */
public interface AudioService {

    void playBroadcast();
    void stopBroadcast();

    class Creator {
        public static AudioService newAudioService(){
            return new AudioServiceImpl("AudioService");
        }
    }
}


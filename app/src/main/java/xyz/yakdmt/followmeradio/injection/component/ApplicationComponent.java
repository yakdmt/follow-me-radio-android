package xyz.yakdmt.followmeradio.injection.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import xyz.yakdmt.followmeradio.injection.ApplicationContext;
import xyz.yakdmt.followmeradio.injection.modules.ApplicationModule;
import xyz.yakdmt.followmeradio.playback.AudioService;

/**
 * Created by yakdmt on 09/04/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(AudioService audioService);

    AudioService audioService();
    @ApplicationContext Context context();

}

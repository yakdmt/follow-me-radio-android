package xyz.yakdmt.followmeradio.injection.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.yakdmt.followmeradio.injection.ApplicationContext;
import xyz.yakdmt.followmeradio.playback.AudioService;

/**
 * Created by yakdmt on 09/04/16.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    AudioService provideAudioService() { return new AudioService(); }

    @ApplicationContext
    @Provides
    Context provideContext() {
        return mApplication;
    }


}

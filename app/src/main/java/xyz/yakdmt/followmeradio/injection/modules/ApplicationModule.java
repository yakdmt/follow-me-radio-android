package xyz.yakdmt.followmeradio.injection.modules;

import android.app.Application;
import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.yakdmt.followmeradio.injection.ApplicationContext;
import xyz.yakdmt.followmeradio.network.NetworkService;
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
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    AudioService provideAudioService() {
        return AudioService.Creator.newAudioService();
    }

    @Provides
    @Singleton
    NetworkService provideNetworkService() {
        return NetworkService.Creator.newNetworkService();
    }

}

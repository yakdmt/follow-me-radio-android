package xyz.yakdmt.followmeradio.injection.modules;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import xyz.yakdmt.followmeradio.injection.ActivityContext;

/**
 * Created by yakdmt on 09/04/16.
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}

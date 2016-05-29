package xyz.yakdmt.followmeradio;

import android.app.Application;
import android.content.Context;

import xyz.yakdmt.followmeradio.injection.component.ApplicationComponent;
import xyz.yakdmt.followmeradio.injection.component.DaggerApplicationComponent;
import xyz.yakdmt.followmeradio.injection.modules.ApplicationModule;

/**
 * Created by yakdmt on 20/04/16.
 */
public class FollowMeApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public static FollowMeApplication get(Context context) {
        return (FollowMeApplication) context.getApplicationContext();
    }


}

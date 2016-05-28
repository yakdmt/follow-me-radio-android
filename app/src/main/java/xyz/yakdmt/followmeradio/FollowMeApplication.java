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

        //if (BuildConfig.DEBUG) {
            //Timber.plant(new Timber.DebugTree());
           // Fabric.with(this, new Crashlytics());
       //}
    }

    public static FollowMeApplication get(Context context) {
        return (FollowMeApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    public void getApplicationComponent(){

    }

}

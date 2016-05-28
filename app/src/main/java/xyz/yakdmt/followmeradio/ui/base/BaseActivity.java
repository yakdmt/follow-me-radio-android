package xyz.yakdmt.followmeradio.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import xyz.yakdmt.followmeradio.FollowMeApplication;
import xyz.yakdmt.followmeradio.injection.component.ActivityComponent;
import xyz.yakdmt.followmeradio.injection.modules.ActivityModule;

/**
 * Created by yakdmt on 09/04/16.
 */
public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(FollowMeApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

}

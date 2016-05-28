package xyz.yakdmt.followmeradio.injection.component;

import dagger.Component;
import xyz.yakdmt.followmeradio.injection.PerActivity;
import xyz.yakdmt.followmeradio.injection.modules.ActivityModule;
import xyz.yakdmt.followmeradio.ui.main.MainActivity;

/**
 * Created by yakdmt on 09/04/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}

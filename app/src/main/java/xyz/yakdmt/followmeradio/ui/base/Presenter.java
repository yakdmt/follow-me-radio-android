package xyz.yakdmt.followmeradio.ui.base;

/**
 * Created by yakdmt on 20/04/16.
 */
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}

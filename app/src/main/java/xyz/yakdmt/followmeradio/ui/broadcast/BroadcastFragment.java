package xyz.yakdmt.followmeradio.ui.broadcast;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import javax.inject.Inject;

import xyz.yakdmt.followmeradio.ui.main.MainPresenter;

/**
 * Created by yakdmt on 09/04/16.
 */
public class BroadcastFragment extends Fragment implements BroadcastMvpView {

    public static final String ARG_PAGE = "ARG_PAGE";
    @Inject MainPresenter mMainPresenter;


    @Override
    public void refreshInfo() {

    }

    public static BroadcastFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        BroadcastFragment fragment = new BroadcastFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

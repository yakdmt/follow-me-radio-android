package xyz.yakdmt.followmeradio.ui.broadcast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.yakdmt.followmeradio.R;
import xyz.yakdmt.followmeradio.data.local.TrackInfo;
import xyz.yakdmt.followmeradio.ui.main.MainPresenter;

/**
 * Created by yakdmt on 09/04/16.
 */
public class BroadcastFragment extends Fragment implements BroadcastMvpView {

    public static final String ARG_PAGE = "ARG_PAGE";
    @Inject MainPresenter mMainPresenter;
    @Bind(R.id.cover) ImageView mCover;
    @Bind(R.id.artist) TextView mArtist;
    @Bind(R.id.song) TextView mSong;

    @Override
    public void refreshInfo(TrackInfo trackInfo) {
        Picasso.with(getActivity()).load(trackInfo.getImageUrl()).into(mCover);
        mArtist.setText(trackInfo.getArtist());
        mSong.setText(trackInfo.getTitle());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_broadcast, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
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
        fillTestData();
    }

    private void fillTestData(){
        TrackInfo trackInfo = new TrackInfo();
        trackInfo.setImageUrl("http://img2-ak.lst.fm/i/u/300x300/128bcaeda6d5472b9bed1edc1fa92af9.png");
        trackInfo.setArtist("Brendan Eder");
        trackInfo.setTitle("Very Long Test Song");
        refreshInfo(trackInfo);
    }
}

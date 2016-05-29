package xyz.yakdmt.followmeradio.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.yakdmt.followmeradio.R;
import xyz.yakdmt.followmeradio.ui.base.BaseActivity;
import xyz.yakdmt.followmeradio.ui.broadcast.BroadcastFragment;
import xyz.yakdmt.followmeradio.ui.podcast.PodcastFragment;

/**
 * Created by yakdmt on 20/04/16.
 */
public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject MainPresenter mMainPresenter;
    MainPagerAdapter mMainPagerAdapter;

    @Bind(R.id.view_pager) ViewPager mViewPager;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tab_layout) TabLayout mTabLayout;
    @Bind(R.id.fab) FloatingActionButton mFab;

    @Override
    public void showError() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        boolean injected = mMainPresenter != null;
        Log.d("dagger", "injected = "+injected);
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);

        mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mMainPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mFab.setOnClickListener(v -> {
            mMainPresenter.onFabClick();
        });

    }

    public class MainPagerAdapter extends FragmentStatePagerAdapter {

        final int PAGE_COUNT = 4;
        private Context mContext;
        private int[] imageResId = {
                R.drawable.ic_radio_white_24dp,
                R.drawable.ic_library_music_white_24dp,
                R.drawable.ic_favorite_white_24dp,
                R.drawable.ic_file_download_white_24dp
        };

        public MainPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            if (position==0) {
                return BroadcastFragment.newInstance(position + 1);
            } else {
                return PodcastFragment.newInstance(position + 1);
            }

        }

        @Override
        public CharSequence getPageTitle(int position) {
            Drawable image = ContextCompat.getDrawable(mContext, imageResId[position]);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
            SpannableString sb = new SpannableString(" ");
            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;
        }
    }

}

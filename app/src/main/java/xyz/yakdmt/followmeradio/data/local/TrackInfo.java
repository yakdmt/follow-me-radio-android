package xyz.yakdmt.followmeradio.data.local;

/**
 * Created by yakdmt on 12/06/16.
 */
public class TrackInfo {

    private String mArtist;
    private String mTitle;
    private String mImageUrl;

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String mArtist) {
        this.mArtist = mArtist;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
}

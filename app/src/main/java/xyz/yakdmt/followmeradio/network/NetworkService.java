package xyz.yakdmt.followmeradio.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;
import xyz.yakdmt.followmeradio.ui.podcast.PodcastModel;

public interface NetworkService {

    String ENDPOINT = "https://yakdmt.xyz/";

    //@GET("podcasts")
    Observable<List<PodcastModel>> getPodcasts();

    class Creator {

        public static NetworkService newNetworkService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(NetworkService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(NetworkService.class);
        }
    }

}
package com.dgorod.example.api;

import com.dgorod.example.api.model.PopularPhotosModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface for 500px API requests.
 *
 * Created by Dmytro Gorodnytskyi on 15-May-16.
 */
public interface ApiInterface {

    @GET("photos")
    Call<PopularPhotosModel> getPhotos(@Query("feature") String feature,
                                       @Query("consumer_key") String consumerKey,
                                       @Query("image_size") String imageSize,
                                       @Query("page") int page);
}

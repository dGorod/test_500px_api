package com.dgorod.example.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import com.dgorod.example.R;
import com.dgorod.example.api.ApiService;
import com.dgorod.example.api.PhotoSizes;
import com.dgorod.example.api.model.PopularPhotosModel;

import java.io.IOException;

import retrofit2.Response;

/**
 * Loads popular photos from 500px API.
 *
 * Created by Dmytro Gorodnytskyi on 15-May-16.
 */
public class PhotosLoader extends AsyncTaskLoader<PopularPhotosModel> {

    private final String API_CALL;
    private final String API_KEY;
    private final String IMAGE_SIZES;

    private int page = 1;

    public PhotosLoader(@NonNull Context context) {
        super(context);

        API_CALL = "popular";
        API_KEY = context.getString(R.string.api_500px_consumer_key);
        IMAGE_SIZES = PhotoSizes.SMALL + "," + PhotoSizes.BIG;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public PopularPhotosModel loadInBackground() {
        try {
            Response<PopularPhotosModel> response = ApiService.getInstance()
                    .getPhotos(API_CALL, API_KEY, IMAGE_SIZES, page)
                    .execute();

            return response.isSuccessful() ? response.body() : null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        page = 1;
    }
}

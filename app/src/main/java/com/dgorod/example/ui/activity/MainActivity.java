package com.dgorod.example.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.dgorod.example.R;
import com.dgorod.example.adapter.PhotosAdapter;
import com.dgorod.example.api.model.PhotoModel;
import com.dgorod.example.api.model.PopularPhotosModel;
import com.dgorod.example.loader.PhotosLoader;
import com.dgorod.example.util.EndlessScrollListener;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<PopularPhotosModel>, SwipeRefreshLayout.OnRefreshListener {

    private static final int LOADER_ID = 101;
    private static final String PHOTOS_KEY = "key_photos";

    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;
    @Bind(R.id.photos)
    GridView photos;

    private PhotosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new PhotosAdapter();

        if (savedInstanceState != null && savedInstanceState.containsKey(PHOTOS_KEY)) {
            List<PhotoModel> data = Parcels.unwrap(savedInstanceState.getParcelable(PHOTOS_KEY));
            adapter.appendData(data);
        }

        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        refresh.setOnRefreshListener(this);
        photos.setOnScrollListener(photoScrollListener);
        photos.setOnItemClickListener(photoItemClickListener);

        photos.setAdapter(adapter);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PHOTOS_KEY, Parcels.wrap(adapter.getData()));
    }

    @Override
    public Loader<PopularPhotosModel> onCreateLoader(int id, Bundle args) {
        return new PhotosLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<PopularPhotosModel> loader, @Nullable PopularPhotosModel data) {
        if (data != null) {
            adapter.appendData(data.getPhotos());
        }
        if (refresh.isRefreshing()) {
            refresh.setRefreshing(false);
        }
    }

    @Override
    public void onLoaderReset(Loader<PopularPhotosModel> loader) {
        adapter.clearData();
    }

    @Override
    public void onRefresh() {
        adapter.clearData();
        loadPopularPhotos(1);
    }

    private void loadPopularPhotos(int page) {
        Loader<PopularPhotosModel> rawLoader = getSupportLoaderManager().getLoader(LOADER_ID);
        PhotosLoader loader = (PhotosLoader) rawLoader;
        loader.setPage(page);
        loader.forceLoad();
    }

    // --- action listeners

    private EndlessScrollListener photoScrollListener = new EndlessScrollListener(5, 1) {
        @Override
        public boolean onLoadMore(int page, int totalItemsCount) {
            loadPopularPhotos(page);
            return true;
        }
    };

    private AdapterView.OnItemClickListener photoItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            startActivity(ViewActivity.start(MainActivity.this, adapter.getItem(position)));
            overridePendingTransition(R.anim.slide_from_bottom, 0);
        }
    };
}

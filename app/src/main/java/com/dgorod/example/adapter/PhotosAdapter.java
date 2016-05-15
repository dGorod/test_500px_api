package com.dgorod.example.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dgorod.example.api.PhotoSizes;
import com.dgorod.example.api.model.PhotoModel;
import com.dgorod.example.util.DimensionUtil;
import com.dgorod.example.util.Preconditions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to load popular photos.
 *
 * Created by Dmytro Gorodnytskyi on 15-May-16.
 */
public class PhotosAdapter extends BaseAdapter {

    private List<PhotoModel> data = new ArrayList<>();

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public PhotoModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView resultView;

        if (convertView == null) {
            int height = DimensionUtil.pxToDp(160f);
            int padding = DimensionUtil.pxToDp(5f);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, height);

            resultView = new ImageView(parent.getContext());
            resultView.setLayoutParams(params);
            resultView.setPadding(padding, padding, padding, padding);
        }
        else {
            resultView = (ImageView) convertView;
        }

        Picasso.with(parent.getContext())
                .load(data.get(position).getImageWithSize(PhotoSizes.SMALL))
                .into(resultView);

        return resultView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    @NonNull
    public List<PhotoModel> getData() {
        return data;
    }

    public void appendData(@NonNull List<PhotoModel> newData) {
        Preconditions.checkNotNull(newData);
        data.addAll(newData);
        notifyDataSetChanged();
    }
}

package com.dgorod.example.api.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmytro Gorodnytskyi on 15-May-16.
 */
public class PopularPhotosModel {

    int current_page;
    int total_pages;
    int total_items;
    List<PhotoModel> photos;

    public int getCurrentPage() {
        return current_page;
    }

    public int getTotalPages() {
        return total_pages;
    }

    public int getTotalItems() {
        return total_items;
    }

    @NonNull
    public List<PhotoModel> getPhotos() {
        return photos == null ? new ArrayList<PhotoModel>(0) : photos;
    }

    @Override
    public String toString() {
        return "PopularPhotosModel{" +
                "current_page=" + current_page +
                ", total_pages=" + total_pages +
                ", total_items=" + total_items +
                ", photos=" + photos +
                '}';
    }
}

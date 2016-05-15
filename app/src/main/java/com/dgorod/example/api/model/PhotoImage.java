package com.dgorod.example.api.model;

import android.support.annotation.Nullable;

import org.parceler.Parcel;

/**
 * Created by Dmytro Gorodnytskyi on 15-May-16.
 */
@Parcel
public class PhotoImage {

    Integer size;
    String https_url;

    @Nullable
    public Integer getSize() {
        return size;
    }

    @Nullable
    public String getUrl() {
        return https_url;
    }

    @Override
    public String toString() {
        return "PhotoImage{" +
                "size=" + size +
                ", https_url='" + https_url + '\'' +
                '}';
    }
}

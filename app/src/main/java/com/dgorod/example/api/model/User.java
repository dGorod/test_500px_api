package com.dgorod.example.api.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.parceler.Parcel;

/**
 * Created by Dmytro Gorodnytskyi on 15-May-16.
 */
@Parcel
public class User {

    Long id;
    String fullname;

    @Nullable
    public Long getId() {
        return id;
    }

    @NonNull
    public String getFullname() {
        return TextUtils.isEmpty(fullname) ? "n/a" : fullname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}

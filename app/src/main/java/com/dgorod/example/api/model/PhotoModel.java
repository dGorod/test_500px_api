package com.dgorod.example.api.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Dmytro Gorodnytskyi on 15-May-16.
 */
@Parcel
public class PhotoModel {

    Long id;
    String name;
    String camera;
    String lens;
    User user;
    List<PhotoImage> images;

    public long getId() {
        return id == null ? -1L : id;
    }

    @NonNull
    public String getName() {
        return TextUtils.isEmpty(name) ? "n/a" : name;
    }

    @NonNull
    public String getCameraInfo() {
        StringBuilder sb = new StringBuilder();

        if (!TextUtils.isEmpty(camera)) {
            sb.append(camera).append(" / ");
        }
        if (!TextUtils.isEmpty(lens)) {
            sb.append(lens);
        }

        return sb.length() == 0 ? " n/a" : sb.toString();
    }

    @Nullable
    public User getUser() {
        return user;
    }

    @Nullable
    public List<PhotoImage> getImages() {
        return images;
    }

    @NonNull
    public String getImageWithSize(int size) {
        if (images == null || images.isEmpty()) {
            return "";
        }
        else {
            String result = images.get(0).getUrl();

            for (PhotoImage photo : images) {
                if (photo.getSize() != null && photo.getSize() == size) {
                    result = photo.getUrl();
                }
            }

            return result == null ? "" : result;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotoModel that = (PhotoModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhotoModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", camera='" + camera + '\'' +
                ", lens='" + lens + '\'' +
                ", user=" + user +
                ", images=" + images +
                '}';
    }
}

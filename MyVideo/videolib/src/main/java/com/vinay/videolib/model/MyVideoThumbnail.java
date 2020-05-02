package com.vinay.videolib.model;

import com.google.gson.annotations.SerializedName;

public class MyVideoThumbnail {

    public @SerializedName("thumbnail_url")
    String thumbnailUrl;

    public MyVideoThumbnail(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}

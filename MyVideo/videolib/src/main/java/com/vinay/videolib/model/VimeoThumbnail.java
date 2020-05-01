package com.vinay.videolib.model;

import com.google.gson.annotations.SerializedName;

public class VimeoThumbnail {

    public @SerializedName("thumbnail_url")
    String thumbnailUrl;

    public VimeoThumbnail(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}

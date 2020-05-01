package com.vinay.videolib.listeners;

public interface VimeoPlayerTextTrackListener {
    void onTextTrackChanged(String kind, String label, String language);
}

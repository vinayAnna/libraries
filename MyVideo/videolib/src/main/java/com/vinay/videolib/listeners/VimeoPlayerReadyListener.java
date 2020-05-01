package com.vinay.videolib.listeners;


import com.vinay.videolib.model.TextTrack;

public interface VimeoPlayerReadyListener {
    void onReady(String title, float duration, TextTrack[] textTrackArray);

    void onInitFailed();
}

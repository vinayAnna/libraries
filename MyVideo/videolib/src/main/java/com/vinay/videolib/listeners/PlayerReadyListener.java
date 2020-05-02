package com.vinay.videolib.listeners;


import com.vinay.videolib.model.TextTrack;

public interface PlayerReadyListener {
    void onReady(String title, float duration, TextTrack[] textTrackArray);

    void onInitFailed();
}

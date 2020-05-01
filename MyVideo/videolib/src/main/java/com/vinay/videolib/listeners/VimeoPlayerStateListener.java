package com.vinay.videolib.listeners;

public interface VimeoPlayerStateListener {
    void onPlaying(float duration);

    void onPaused(float seconds);

    void onEnded(float duration);
}

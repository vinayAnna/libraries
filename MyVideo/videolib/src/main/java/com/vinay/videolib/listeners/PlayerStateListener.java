package com.vinay.videolib.listeners;

public interface PlayerStateListener {
    void onPlaying(float duration);

    void onPaused(float seconds);

    void onEnded(float duration);
}

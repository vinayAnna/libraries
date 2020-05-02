package com.vinay.videolib.utils;

import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.vinay.videolib.listeners.PlayerErrorListener;
import com.vinay.videolib.listeners.PlayerReadyListener;
import com.vinay.videolib.listeners.PlayerStateListener;
import com.vinay.videolib.listeners.PlayerTextTrackListener;
import com.vinay.videolib.listeners.PlayerTimeListener;
import com.vinay.videolib.listeners.PlayerVolumeListener;
import com.vinay.videolib.model.PlayerState;
import com.vinay.videolib.model.TextTrack;

import java.util.ArrayList;


public class JsBridge {
    private final Handler mainThreadHandler;
    private ArrayList<PlayerReadyListener> readyListeners;
    private ArrayList<PlayerStateListener> stateListeners;
    private ArrayList<PlayerTextTrackListener> textTrackListeners;
    private ArrayList<PlayerTimeListener> timeListeners;
    private ArrayList<PlayerVolumeListener> volumeListeners;
    private ArrayList<PlayerErrorListener> errorListeners;


    public float currentTimeSeconds = 0;
    public PlayerState playerState = PlayerState.UNKNOWN;
    public float volume = 1;

    public JsBridge() {
        readyListeners = new ArrayList<>();
        stateListeners = new ArrayList<>();
        textTrackListeners = new ArrayList<>();
        timeListeners = new ArrayList<>();
        volumeListeners = new ArrayList<>();
        errorListeners = new ArrayList<>();
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    public void removeLastReadyListener(PlayerReadyListener readyListener) {
        this.readyListeners.remove(readyListener);
    }

    public void addReadyListener(PlayerReadyListener readyListener) {
        this.readyListeners.add(readyListener);
    }

    public void addStateListener(PlayerStateListener stateListener) {
        this.stateListeners.add(stateListener);
    }

    public void addTextTrackListener(PlayerTextTrackListener textTrackListener) {
        this.textTrackListeners.add(textTrackListener);
    }

    public void addTimeListener(PlayerTimeListener timeListener) {
        this.timeListeners.add(timeListener);
    }

    public void addVolumeListener(PlayerVolumeListener volumeListener) {
        this.volumeListeners.add(volumeListener);
    }

    public void addErrorListener(PlayerErrorListener errorListener) {
        this.errorListeners.add(errorListener);
    }


    @JavascriptInterface
    public void sendVideoCurrentTime(float seconds) {
        currentTimeSeconds = seconds;
        for (final PlayerTimeListener timeListener : timeListeners) {
            mainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    timeListener.onCurrentSecond(currentTimeSeconds);
                }
            });
        }
    }

    @JavascriptInterface
    public void sendError(final String message, final String method, final String name) {
        for (final PlayerErrorListener errorListener : errorListeners) {
            mainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    errorListener.onError(message, method, name);
                }
            });
        }
    }


    @JavascriptInterface
    public void sendReady(final String title, final float duration, final String tracksJson) {
        this.playerState = PlayerState.READY;
        final TextTrack[] textTrackArray = new Gson().fromJson(tracksJson, TextTrack[].class);
        for (final PlayerReadyListener readyListener : readyListeners) {
            mainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    readyListener.onReady(title, duration, textTrackArray);
                }
            });
        }
    }

    @JavascriptInterface
    public void sendInitFailed() {
        for (final PlayerReadyListener readyListener : readyListeners) {
            mainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    readyListener.onInitFailed();
                }
            });
        }
    }


    @JavascriptInterface
    public void sendPlaying(final float duration) {
        playerState = PlayerState.PLAYING;
        for (final PlayerStateListener stateListener : stateListeners) {
            mainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    stateListener.onPlaying(duration);
                }
            });
        }
    }


    @JavascriptInterface
    public void sendPaused(final float seconds) {
        playerState = PlayerState.PAUSED;
        for (final PlayerStateListener stateListener : stateListeners) {
            mainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    stateListener.onPaused(seconds);
                }
            });
        }
    }

    @JavascriptInterface
    public void sendEnded(final float duration) {
        playerState = PlayerState.ENDED;
        for (final PlayerStateListener stateListener : stateListeners) {
            mainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    stateListener.onEnded(duration);
                }
            });
        }
    }


    @JavascriptInterface
    public void sendVolumeChange(final float volume) {
        this.volume = volume;
        for (final PlayerVolumeListener volumeListener : volumeListeners) {
            mainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    volumeListener.onVolumeChanged(volume);
                }
            });
        }
    }

    @JavascriptInterface
    public void sendTextTrackChange(final String kind, final String label, final String language) {
        for (final PlayerTextTrackListener textTrackListener : textTrackListeners) {
            mainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    textTrackListener.onTextTrackChanged(kind, label, language);
                }
            });
        }
    }

}

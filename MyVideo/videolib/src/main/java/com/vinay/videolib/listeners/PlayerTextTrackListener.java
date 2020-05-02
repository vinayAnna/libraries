package com.vinay.videolib.listeners;

public interface PlayerTextTrackListener {
    void onTextTrackChanged(String kind, String label, String language);
}

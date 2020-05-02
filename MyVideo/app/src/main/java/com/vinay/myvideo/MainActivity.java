package com.vinay.myvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vinay.videolib.listeners.PlayerErrorListener;
import com.vinay.videolib.listeners.PlayerTimeListener;
import com.vinay.videolib.view.VimeoPlayerView;

public class MainActivity extends AppCompatActivity {
    VimeoPlayerView vimeoPlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vimeoPlayerView=findViewById(R.id.vimeoPlayerView);
        setupView();
    }
    private void setupView() {

        vimeoPlayerView.initialize(true, 59777392);

        vimeoPlayerView.addTimeListener(new PlayerTimeListener() {
            @Override
            public void onCurrentSecond(float second) {

            }
        });

        vimeoPlayerView.addErrorListener(new PlayerErrorListener() {
            @Override
            public void onError(String message, String method, String name) {

            }
        });



    }
}

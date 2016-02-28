package com.chest.blue.radio;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StreamHD1 extends Activity {

    public static MediaPlayer stream;
    public static String url;
    public static Button controlButton;
    public static Button haltButton;
    public static Intent svc;
    public static String status;
    public int activeStation;
    public static int serviceAvailable;
    private final Handler mHandler = new Handler();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screenhd1);
        controlButton = (Button) findViewById(R.id.stream_control);
        controlButton.setOnClickListener(controlClickListener);
        url = getString(R.string.stream_pls_HD1);
        stream = StreamService.stream;
        svc = new Intent(StreamHD1.this, StreamService.class);
        svc.putExtra("url", url);
        svc.putExtra("activeStation", 1);

        mHandler.postDelayed(initializeStream, 2 * 1000);

    }

    @Override
    public void onStart() {
        super.onStart();
        // initializeStream();
    }

    public void toggleControlButton(Button button, String state) {
        MainActivity.toggleControlButton(button, state);
    }

    public OnClickListener controlClickListener = new OnClickListener() {
        public void onClick(View v) {
            stream = StreamService.stream;
            activeStation = MainActivity.activeStation;
            status = "STATUS";
            if (stream != null) {
                if (activeStation == 1) {
                    if (stream.isPlaying() == true) {
                        stopService(svc);
                        toggleControlButton(controlButton, "play");
                    } else {
                        toggleControlButton(controlButton, "loading");
                        startService(svc);
                    }
                }
            }
        }
    };

    private final Runnable initializeStream = new Runnable() {
        public void run() {

            serviceAvailable = StreamService.serviceAvailable;
            if (serviceAvailable == 1) {
            } else {
                startService(svc);
            }
        }
    };
}
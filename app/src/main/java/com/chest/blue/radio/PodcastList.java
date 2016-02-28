package com.chest.blue.radio;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PodcastList extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the Podcast tab");
        setContentView(textview);
    }
}
